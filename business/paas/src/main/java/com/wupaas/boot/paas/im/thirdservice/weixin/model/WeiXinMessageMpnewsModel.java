package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /图文消息（mpnews）
 */
@Data
public class WeiXinMessageMpnewsModel extends WeiXinMessageModel{
    /**
     *  "mpnews" : {
     *        "articles":[
     *            {
     *                "title": "Title",
     *                "thumb_media_id": "MEDIA_ID",
     *                "author": "Author",
     *                "content_source_url": "URL",
     *                "content": "Content",
     *                "digest": "Digest description"
     *             }
     *        ]
     *    },
     */
    private Map<String,Object> mpnews;
}
