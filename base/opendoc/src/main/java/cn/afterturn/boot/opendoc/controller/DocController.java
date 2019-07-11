package cn.afterturn.boot.opendoc.controller;

import cn.afterturn.boot.opendoc.model.SwaggerProjectModel;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by jueyue on 19-1-15.
 */
@Slf4j
@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private GatewayProperties gatewayProperties;

    private Map<String, String> prefixMap = new HashMap<>();


    @GetMapping("/services")
    public List<SwaggerProjectModel> serviceUrl() {
        List<ServiceInstance>     serviceInstanceList = getServiceInstanceList();
        List<SwaggerProjectModel> services            = new ArrayList<>();
        SwaggerProjectModel       model;
        for (int i = 0; i < serviceInstanceList.size(); i++) {
            try {
                if (serviceInstanceList.get(i).getServiceId().equalsIgnoreCase("lemur-gateway")
                        || serviceInstanceList.get(i).getServiceId().equalsIgnoreCase("lemur-opendoc")) {
                    continue;
                }
                log.debug("start get third services success");
                List<SwaggerProjectModel> tempList = JSON.parseArray(HttpUtil.get(serviceInstanceList.get(i).getUri().toString() + "/swagger-resources", 60 * 1000), SwaggerProjectModel.class);
                log.debug("end   get third services success");
                for (int j = 0; j < tempList.size(); j++) {
                    model = tempList.get(j);
                    model.setName(serviceInstanceList.get(i).getServiceId() + ":" + model.getName());
                    model.setUrl("/doc/group/" + serviceInstanceList.get(i).getServiceId() + "/" + getGroup(model.getUrl()));
                    model.setPrefix(prefixMap.get(getRealServiceName(serviceInstanceList.get(i).getServiceId())));
                    model.setBasePath(prefixMap.get(getRealServiceName(serviceInstanceList.get(i).getServiceId())));
                    services.add(model);
                }

            } catch (Exception e) {
            }
        }
        log.debug("get services success");
        return services;
    }

    private String getRealServiceName(String serviceId) {
        return serviceId.replace("lemur-", "");
    }

    private String getGroup(String url) {
        return HttpUtil.decodeParamMap(url, "UTF8").get("group");
    }

    @GetMapping("/group/{serviceId}/{group}")
    public String group(@PathVariable String serviceId, @PathVariable String group) {
        if (group.equalsIgnoreCase("dubbo")) {
            return HttpUtil.get(discoveryClient.getInstances(serviceId).get(0).getUri().toString() + "/swagger-dubbo/api-docs");
        }
        return HttpUtil.get(discoveryClient.getInstances(serviceId).get(0).getUri().toString() + "/v2/api-docs?group=" + HttpUtil.encodeUtf8(group));
    }


    private List<ServiceInstance> getServiceInstanceList() {
        List<ServiceInstance> msl      = new ArrayList<>();
        List<String>          services = discoveryClient.getServices();
        for (String service : services) {
            try {
                List<ServiceInstance> sis = discoveryClient.getInstances(service);
                if (!CollectionUtils.isEmpty(sis)) {
                    msl.add(sis.get(0));
                }
            } catch (Exception e) {
            }
        }
        return msl;
    }


    //gatewayProperties.getRoutes().get(0).getPredicates().get(0).getArgs().values()
    //gatewayProperties.getRoutes().get(0).getPredicates().get(0).getName()
    @PostConstruct
    private void getPrefixMap() {
        RouteDefinition route;
        for (int i = 0; i < gatewayProperties.getRoutes().size(); i++) {
            route = gatewayProperties.getRoutes().get(i);
            for (int j = 0; j < route.getPredicates().size(); j++) {
                if (route.getPredicates().get(j).getName().equalsIgnoreCase("path")) {
                    prefixMap.put(route.getId(), getPrefix(route.getPredicates().get(j).getArgs()));
                }
            }
        }
    }

    private String getPrefix(Map<String, String> args) {
        for (String val : args.values()) {
            return val.substring(1, val.indexOf("/", 1));
        }
        return "";
    }

}
