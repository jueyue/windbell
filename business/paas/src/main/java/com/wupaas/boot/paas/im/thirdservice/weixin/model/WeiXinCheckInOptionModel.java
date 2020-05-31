package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/24
 * <p>
 * /
 * /**
 *
 * 获取打卡规则
 */
@Data
public class WeiXinCheckInOptionModel {
    /**
     * 是	需要获取规则的日期当天0点的Unix时间戳
     */
   private Long datetime;
    /**
     * 	是	需要获取打卡规则的用户列表
     */
    private List<String> useridlist;
}
