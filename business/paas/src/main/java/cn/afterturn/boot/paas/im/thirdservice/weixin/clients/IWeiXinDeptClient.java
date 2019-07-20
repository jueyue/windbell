package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinDeptModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.dept.WeiXinDeptListResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.dept.WeiXinDeptResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信 部门API接口
 * @author by dbinary on 2019/7/19.
 */
@FeignClient(name = "weixinUserClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/department/",configuration = WeiXinConfiguration.class)
public interface IWeiXinDeptClient {
    /**
     * 创建部门
     * @param deptModel
     * @return WeiXinDeptResultModel
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    WeiXinDeptResultModel create(@RequestBody WeiXinDeptModel deptModel );
    /**
     * 更新部门
     * 必须有父类部门ID
     * @param deptModel
     * @return WeXinBaseResultModel
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    WeXinBaseResultModel update(@RequestBody WeiXinDeptModel deptModel );
    /**
     * 删除部门
     * @param deptId
     * @return WeXinBaseResultModel
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    WeXinBaseResultModel delete(@RequestParam("id") String deptId );
    /**
     * 获取部门列表
     * @param deptId
     * @return WeiXinDeptListResultModel
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    WeiXinDeptListResultModel list(@RequestParam("id") String deptId );
}
