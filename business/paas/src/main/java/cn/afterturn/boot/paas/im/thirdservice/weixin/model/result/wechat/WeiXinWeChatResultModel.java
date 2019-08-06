package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.wechat;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /
 * /**群聊
 *
 */
@Data
public class WeiXinWeChatResultModel extends WeXinBaseResultModel {
    /**
     * 群聊的唯一标志
     */
    private String chatid;
}
