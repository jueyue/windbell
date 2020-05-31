package com.wupaas.boot.paas.im.thirdservice.dingtalk.clients;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.config.DingTalkConfiguration;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by jueyue on 19-7-17.
 */
@FeignClient(name = "dingTalkRoleClient", contextId = "dingtalk", url = "https://oapi.dingtalk.com/role/", configuration = DingTalkConfiguration.class)
public interface IDingTalkRoleClient {


    /**
     * 创建角色
     *
     * @param roleName 名称
     * @param groupId  组ID
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/add_role", method = RequestMethod.POST)
    DingTalkBaseResultModel addRole(@RequestParam("roleName") String roleName, @RequestParam("groupId") String groupId);

    /**
     * 更新角色
     *
     * @param roleName 名称
     * @param roleId   ID
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/update_role", method = RequestMethod.POST)
    DingTalkBaseResultModel updateRole(@RequestParam("roleName") String roleName, @RequestParam("roleId") String roleId);

    /**
     * 创建角色组
     *
     * @param name name
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/add_role_group", method = RequestMethod.POST)
    DingTalkBaseResultModel addRoleGroup(@RequestParam("name") String name);
}
