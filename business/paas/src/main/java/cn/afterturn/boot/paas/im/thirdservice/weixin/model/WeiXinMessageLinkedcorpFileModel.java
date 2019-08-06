package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/23
 *
 */
@Data
public class WeiXinMessageLinkedcorpFileModel extends  WeiXinMessageLinkedcorpModel {
    /**
     * 
     */
    private Map<String,Object> file;
}
