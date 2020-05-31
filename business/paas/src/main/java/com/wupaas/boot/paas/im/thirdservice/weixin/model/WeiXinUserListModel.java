package com.wupaas.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.List;

/**
 * 批量处理用户逻辑 ：批量删除用户
 * @author by dbinary on 2019/7/19.
 */
@Data
public class WeiXinUserListModel   {
    /**
     * 用户userid列表
     */
    private  List<String> useridlist;
    public WeiXinUserListModel(List<String> useridlist){
        this.useridlist = useridlist;
    }
}
