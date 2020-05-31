package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinUserListModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinUserModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.*;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinUserListResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinUserOpenIdUserIdResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.user.WeiXinUserResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 微信 用户API接口
 * 企业微信用户管理客户端
 *
 * @author by dbinary on 19-6-30.
 */
@FeignClient(name = "weixinUserClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/user",configuration = WeiXinConfiguration.class)
public interface IWeiXinUserClient {

    /**
     * 创建用户
     *
     * @param userModel
     * @return WeiXinUserResultModel
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    WeiXinUserResultModel create(@RequestBody WeiXinUserModel userModel );
    /**
     * 读取客户详情
     * @param userid 员工id
     * @param
     * @return WeiXinUserModel
     **/
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    WeiXinUserResultModel get(@RequestParam("userid") String userid);
    /**
     * 更新用户
     *
     * @param userModel
     * @return WeiXinUserResultModel
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    WeiXinUserResultModel update(@RequestBody WeiXinUserModel userModel);

    /**
     * 删除用户
     *
     * @param userid 员工id
     * @return WeXinBaseResultModel
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    WeXinBaseResultModel delete(@RequestParam("userid") String userid);
    /**
     * 批量删除用户
     *
     * @param model 成员UserID列表。对应管理端的帐号。最多支持200个。若存在无效UserID，直接返回错误
     * @return WeXinBaseResultModel
     **/
    @RequestMapping(value = "/batchdelete", method = RequestMethod.POST)
    WeXinBaseResultModel batchdelete(@RequestBody WeiXinUserListModel model );


    /**
     * 获取部门下用户
     * @param departmentid 员工id
     * @param fetchchild 1/0：是否递归获取子部门下面的成员
     * @return WeiXinUserModel
     **/
    @RequestMapping(value = "/simplelist", method = RequestMethod.GET)
    WeiXinUserListResultModel simplelist(@RequestParam("department_id") String departmentid, @RequestParam("fetch_child") String fetchchild);
    /**
     * 获取部门成员详情
     * @param departmentid 员工id
     * @param fetchchild 1/0：是否递归获取子部门下面的成员
     * @return WeiXinUserModel
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    WeiXinUserListResultModel list(@RequestParam("department_id") String departmentid, @RequestParam("fetch_child") String fetchchild);

    /**
     * userid转openid
     * @param map
     * map.put("userid","342");
     * @return WeiXinUserOpenIdUserIdResultModel
     **/
    @RequestMapping(value = "/convert_to_openid", method = RequestMethod.POST)
    WeiXinUserOpenIdUserIdResultModel convert2openid(@RequestBody Map<String,Object> map);
    /**
     * openid转userid
     * @param map
     * map.put("openid","oCuJ50VojHaVn9Gdvm2iVnCRE9cU");
     * @return WeiXinUserOpenIdUserIdResultModel
     **/
    @RequestMapping(value = "/convert_to_userid", method = RequestMethod.POST)
    WeiXinUserOpenIdUserIdResultModel convert2userid( @RequestBody Map<String,Object> map);
    /**
     *二次验证
     * @param userid
     *
     * @return WeXinBaseResultModel
     **/
    @RequestMapping(value = "/authsucc", method = RequestMethod.GET)
    WeXinBaseResultModel authsucc( @RequestParam("userid") String userid);



}
