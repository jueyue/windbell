package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import com.alibaba.fastjson.annotation.JSONField;
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
public class WeiXinWeChatUpdateModel extends WeiXinWeChatModel{
    /**
     * 添加成员的id列表
     */
    @JSONField(name = "add_user_list")
   private  List<String > addUserList;
    /**
     * 踢出成员的id列表
     */
    @JSONField(name = "del_user_list")
   private List<String > delUserList;

}
