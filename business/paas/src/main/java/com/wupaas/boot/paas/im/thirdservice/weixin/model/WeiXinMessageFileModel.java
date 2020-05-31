package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 *     文件消息
 */
@Data
public class WeiXinMessageFileModel extends WeiXinMessageModel{
    /**
     *  "file" : {
     *         "media_id" : "1Yv-zXfHjSjU-7LH-GwtYqDGS-zz6w22KmWAT5COgP7o"
     *    },
     */
    private Map<String,Object> file;
}
