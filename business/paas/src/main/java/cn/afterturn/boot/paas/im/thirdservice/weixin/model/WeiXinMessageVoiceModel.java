package cn.afterturn.boot.paas.im.thirdservice.weixin.model;
/**
 * @author dbinary 2019/7/21.
 */

import lombok.Data;

import java.util.Map;

@Data
public class WeiXinMessageVoiceModel extends WeiXinMessageModel {
    private Map<String,Object> voice ;
}
