package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.tag;

import com.wupaas.boot.paas.im.thirdservice.weixin.model.WeiXinUserModel;
import com.wupaas.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author by dbinary on 2019/7/20.
 */
@Data
public class WeiXinTagUserResultModel extends WeXinBaseResultModel {
    /**
     * 标签中包含的成员列表
     */
    List<WeiXinUserModel> userlist;
    /**
     * 标签名
     */
    private String tagname;
}
