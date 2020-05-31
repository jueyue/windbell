package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/23
 *
 */
@Data
public class WeiXinMessageLinkedcorpMiniprogramModel extends  WeiXinMessageLinkedcorpModel {
    /**
     * 
     */
    @JSONField(name = "miniprogram_notice")
    private Map<String,Object> miniprogramNotice;
}
