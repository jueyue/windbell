package com.wupaas.boot.paas.im.thirdservice.dingtalk.clients;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.config.DingTalkConfiguration;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 不明白钉钉为啥有两个地址
 *
 * @author by jueyue on 19-7-18.
 */
@FeignClient(name = "dingTalkTopApiRoleClient", contextId = "dingtalk", url = "https://oapi.dingtalk.com/topapi/role", configuration = DingTalkConfiguration.class)
public interface IDingTalkTopApiRoleClient {
    /**
     * 获取角色列表
     *
     * @param size   数量
     * @param offset 偏移量
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    DingTalkBaseResultModel list(@RequestParam("size") String size, @RequestParam("offset") String offset);

    /**
     * 获取角色下的员工列表
     *
     * @param role_id ID
     * @param size    数据
     * @param offset  偏移量
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/simplelist", method = RequestMethod.POST)
    DingTalkBaseResultModel simplelist(@RequestParam("role_id") String role_id, @RequestParam("size") String size, @RequestParam("offset") String offset);

    /**
     * 获取角色组
     *
     * @param group_id ID
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/getrolegroup", method = RequestMethod.POST)
    DingTalkBaseResultModel getrolegroup(@RequestParam("group_id") String group_id);

    /**
     * 获取角色详情
     *
     * @param roleId ID
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/getrole", method = RequestMethod.POST)
    DingTalkBaseResultModel getrole(@RequestParam("roleId") String roleId);


    /**
     * 删除角色
     *
     * @param role_id ID
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/deleterole", method = RequestMethod.POST)
    DingTalkBaseResultModel deleterole(@RequestParam("role_id") String role_id);


    /**
     * 批量增加员工角色
     *
     * @param roleIds IDS
     * @param userIds IDS
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/addrolesforemps", method = RequestMethod.POST)
    DingTalkBaseResultModel addrolesforemps(@RequestParam("roleIds") String roleIds, @RequestParam("userIds") String userIds);

    /**
     * 批量删除员工角色
     *
     * @param roleIds IDS
     * @param userIds IDS
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/removerolesforemps", method = RequestMethod.POST)
    DingTalkBaseResultModel removerolesforemps(@RequestParam("roleIds") String roleIds, @RequestParam("userIds") String userIds);
}
