package com.wupaas.boot.paas.im.thirdservice.weixin.clients;

import com.wupaas.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinTagModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinTagUserListModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagListResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagUserListResultModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagUserResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 标签服务
 * @author by dbinary on 2019/7/20.
 */
@FeignClient(name = "weiXinTagClient",contextId = "weixin", url = "https://qyapi.weixin.qq.com/cgi-bin/tag",configuration = WeiXinConfiguration.class)
public interface IWeiXinTagClient {
    /**
     * 创建tag
     * @param model
     * @return
     */
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    WeiXinTagResultModel create(@RequestBody WeiXinTagModel model );

    /**
     * 更新tag
     * @param model
     * @return
     */
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    WeXinBaseResultModel update(@RequestBody WeiXinTagModel model );

    /**
     * 删除tag
     * @param tagid
     * @return
     */
    @RequestMapping(value = "/delete" , method = RequestMethod.GET)
    WeXinBaseResultModel delete(@RequestParam("tagid") String  tagid );
    /**
     * 获取标签成员
     * @param tagid
     * @return
     */
    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    WeiXinTagUserResultModel get(@RequestParam("tagid") String  tagid );
    /**
     * 添加标签成员
     * @param model
     * @return
     */
    @RequestMapping(value = "/addtagusers" , method = RequestMethod.POST)
    WeiXinTagUserListResultModel addtagusers(@RequestBody WeiXinTagUserListModel model );
    /**
     * 删除标签成员
     * @param model
     * @return
     */
    @RequestMapping(value = "/deltagusers" , method = RequestMethod.POST)
    WeiXinTagUserListResultModel deltagusers(@RequestBody WeiXinTagUserListModel model );
    /**
     * 获取标签成员
     * @param tagid
     * @return
     */
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    WeiXinTagListResultModel list(@RequestParam("tagid") String  tagid );
}
