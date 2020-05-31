package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.wechat;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /
 * /**
 *
 * @author by Administrator on 2019/7/22.
 */
@Data
public class WeiXinWeChatInfoResultModel extends WeXinBaseResultModel {
    /**
     * 群聊信息
     */
    @JSONField( name ="chat_info")
    private List<WeiXinWeChatInfoResultDetailModel> chatInfo;
}
