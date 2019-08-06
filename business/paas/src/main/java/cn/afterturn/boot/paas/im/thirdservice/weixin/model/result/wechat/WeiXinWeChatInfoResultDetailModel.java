package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.wechat;

import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/22
 * <p>
 * /
 * /**
 *
 */
@Data
public class WeiXinWeChatInfoResultDetailModel {

    /**
     * 群聊名，最多50个utf8字符，超过将截断
     */
    private String  name;
    /**
     * 否	指定群主的id。如果不指定，系统会随机从userlist中选一人作为群主
     */
    private String   owner;

    /**
     * 否	群聊的唯一标志，不能与已有的群重复；字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z。如果不填，系统会随机生成群id
     */
    private String   chatid;
    /**
     *群成员id列表
     */
    private List<String > userlist;
}
