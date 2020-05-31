package com.wupaas.boot.paas.im.thirdservice.weixin.model;

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
public class WeiXinWeChatInfoCreateModel extends WeiXinWeChatModel {

    /**
     * 是	群成员id列表。至少2人，至多500人
     */
    private List<String>   userlist;
}
