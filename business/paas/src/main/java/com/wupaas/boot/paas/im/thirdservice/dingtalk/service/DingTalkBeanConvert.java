package com.wupaas.boot.paas.im.thirdservice.dingtalk.service;

import com.wupaas.boot.paas.im.controller.model.PaasUserRequestModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.model.DingTalkUserModel;

import java.util.ArrayList;

/**
 * bean转换类
 *
 * @author jueyue on 20-2-10.
 */
public class DingTalkBeanConvert {

    public static DingTalkUserModel toUser(PaasUserRequestModel model) {
        DingTalkUserModel user = new DingTalkUserModel();
        user.setUserid(model.getId());
        user.setName(model.getName());
        user.setDepartment(new ArrayList() {{
            add(model.getDeptId());
        }});
        user.setMobile(model.getPhone());
        user.setEmail(model.getEmail());
        return user;
    }
}
