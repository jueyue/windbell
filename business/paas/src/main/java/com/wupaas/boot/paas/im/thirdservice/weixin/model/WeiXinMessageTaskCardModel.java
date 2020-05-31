package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /
 * /**
 *任务卡片消息
 */
@Data
public class WeiXinMessageTaskCardModel extends  WeiXinMessageModel {
    /**
     * "taskcard" : {
     *             "title" : "赵明登的礼物申请",
     *             "description" : "礼品：A31茶具套装<br>用途：赠与小黑科技张总经理",
     *             "url" : "URL",
     *             "task_id" : "taskid123",
     *             "btn":[
     *                 {
     *                     "key": "key111",
     *                     "name": "批准",
     *                     "replace_name": "已批准",
     *                     "color":"red",
     *                     "is_bold": true
     *                 },
     *                 {
     *                     "key": "key222",
     *                     "name": "驳回",
     *                     "replace_name": "已驳回"
     *                 }
     *             ]
     *    }
     */
    private Map<String,Object> taskcard;
}
