package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.List;

/**
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserInviteModel {
    /**
     * 成员ID列表, 最多支持1000个。
     */
    List<String > user;
    /**
     *部门ID列表，最多支持100个。
     */
    List<Integer > party;
    /**
     * 标签ID列表，最多支持100个。
     */
    List<Integer > tag;

}
