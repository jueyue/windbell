package com.wupaas.boot.paas.im.thirdservice.dingtalk.clients;

import com.wupaas.boot.paas.im.thirdservice.dingtalk.config.DingTalkConfiguration;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.DingTalkUserModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.user.DingTalkUserCommonResultModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.user.DingTalkUserListResultModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.result.user.DingTalkUserResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户相关接口
 *
 * @author by jueyue on 19-7-15.
 */
@FeignClient(name = "dingTalkUserClient", contextId = "dingtalk", url = "https://oapi.dingtalk.com/user/", configuration = DingTalkConfiguration.class)
public interface IDingTalkUserClient {

    /**
     * 创建用户
     *
     * @param userModel
     * @return DingTalkUserResultModel
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    DingTalkUserResultModel create(@RequestBody DingTalkUserModel userModel);

    /**
     * 更新用户
     *
     * @param userModel
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    DingTalkBaseResultModel update(@RequestBody DingTalkUserModel userModel);

    /**
     * 删除用户
     *
     * @param userid 员工id
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    DingTalkBaseResultModel delete(@RequestParam("userid") String userid);

    /**
     * 获取用户详情
     *
     * @param userid 员工id
     * @param lang   通讯录语言(默认zh_CN，未来会支持en_US)
     * @return DingTalkUserResultModel
     **/
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    DingTalkUserResultModel get(@RequestParam("userid") String userid, @RequestParam(name = "lang", required = false) String lang);

    /**
     * 获取部门用户userid列表
     *
     * @param deptId 部门id
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/getDeptMember", method = RequestMethod.GET)
    DingTalkBaseResultModel getDeptMember(@RequestParam("deptId") String deptId);

    /**
     * 获取部门用户
     *
     * @param lang          通讯录语言(默认zh_CN另外支持en_US)ZACa
     * @param department_id 获取的部门id
     * @param offset        支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size          支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
     * @param order         支持分页查询，部门成员的排序规则，默认不传是按自定义排序； entry_asc：代表按照进入部门的时间升序， entry_desc：代表按照进入部门的时间降序， modify_asc：代表按照部门信息修改时间升序， modify_desc：代表按照部门信息修改时间降序， custom：代表用户定义(未定义时按照拼音)排序
     * @return DingTalkUserListResultModel
     **/
    @RequestMapping(value = "/simplelist", method = RequestMethod.GET)
    DingTalkUserListResultModel simplelist(@RequestParam("lang") String lang, @RequestParam("department_id") String department_id, @RequestParam("offset") String offset, @RequestParam("size") String size, @RequestParam("order") String order);

    /**
     * 获取部门用户详情
     *
     * @param lang          通讯录语言(默认zh_CN另外支持en_US)
     * @param department_id 获取的部门id，1表示根部门
     * @param offset        支持分页查询，与size参数同时设置时才生效，此参数代表偏移量,偏移量从0开始
     * @param size          支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
     * @param order         支持分页查询，部门成员的排序规则，默认 是按自定义排序； entry_asc：代表按照进入部门的时间升序， entry_desc：代表按照进入部门的时间降序， modify_asc：代表按照部门信息修改时间升序， modify_desc：代表按照部门信息修改时间降序， custom：代表用户定义(未定义时按照拼音)排序
     * @return DingTalkUserListResultModel
     **/
    @RequestMapping(value = "/listbypage", method = RequestMethod.GET)
    DingTalkUserListResultModel listbypage(@RequestParam("lang") String lang, @RequestParam("department_id") String department_id, @RequestParam("offset") String offset, @RequestParam("size") String size, @RequestParam("order") String order);

    /**
     * 获取管理员列表
     *
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/get_admin", method = RequestMethod.GET)
    DingTalkBaseResultModel getAdmin();

    /**
     * 获取管理员通讯录权限范围
     *
     * @param userid 员工id
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/https://oapi.dingtalk.com/topapi/user/get_admin_scope", method = RequestMethod.GET)
    DingTalkUserCommonResultModel getAdminScope(@RequestParam("userid") String userid);

    /**
     * 根据unionid获取userid
     *
     * @param unionid 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/getUseridByUnionid", method = RequestMethod.GET)
    DingTalkUserResultModel getUseridByUnionid(@RequestParam("unionid") String unionid);

    /**
     * 获取企业员工人数
     *
     * @param onlyActive 0：包含未激活钉钉的人员数量 1：不包含未激活钉钉的人员数量
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/get_org_user_count", method = RequestMethod.GET)
    DingTalkUserCommonResultModel getOrgUserCount(@RequestParam("onlyActive") String onlyActive);
}
