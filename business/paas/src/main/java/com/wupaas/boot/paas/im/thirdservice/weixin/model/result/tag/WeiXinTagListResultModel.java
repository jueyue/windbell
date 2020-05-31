package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;

import java.util.List;

/**
 * Created by dbinary on 2019/7/20
 * <p>
 * /
 */
public class WeiXinTagListResultModel extends WeXinBaseResultModel {
    /**
     * 标签列表
     */
    private List<WeiXinTagResultModel> taglist;
}
