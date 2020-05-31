package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/20
 * <p>
 */
@Data
public class WeiXinTagUserListModel {
    /**
     * 是	标签ID
     */
   private String  tagid;
    /**
     *否	企业成员ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过1000
     */
    private List<String> userlist;
    /**
     *否	企业部门ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过100
     */
    private List<Integer> partylist;
}
