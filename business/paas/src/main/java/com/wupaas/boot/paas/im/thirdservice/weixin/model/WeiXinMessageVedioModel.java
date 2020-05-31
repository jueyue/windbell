package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * @author dbinary 2019/7/21.
 */
@Data
public class WeiXinMessageVedioModel extends WeiXinMessageModel{
    /**
     *   "video" : {"media_id" : "MEDIA_ID",
     *         "title" : "Title",
     *        "description" : "Description"
     *        }
     */
    private Map<String,Object> vedio;
}
