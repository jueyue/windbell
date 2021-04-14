package com.wupaas.boot.paas.im.controller;

import com.wupaas.boot.core.business.request.RequestParams;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.wupaas.boot.paas.common.context.ThirdServiceContext;
import com.wupaas.boot.paas.common.enums.PaasEnum;
import com.wupaas.boot.paas.im.controller.model.PaasUserRequestModel;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.clients.IDingTalkUserClient;
import com.wupaas.boot.paas.im.thirdservice.dingtalk.service.DingTalkBeanConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.wupaas.boot.paas.common.Constant.PAAS;

/**
 * @author by jueyue on 19-6-30.
 */
@Api("Paas用户管理")
@RestController
@RequestMapping(PAAS + "/im/user")
public class ImUserController {

    @Autowired
    private IDingTalkUserClient dingTalkUserClient;

    public Response<Page<PaasUserRequestModel>> list(RequestParams<PaasUserRequestModel> params) {
        return null;
    }

    public Response create(@Valid PaasUserRequestModel model) {
        PaasEnum paas = ThirdServiceContext.getAppEnum(model.getTenantId());
        switch (paas) {
            case DING_TALK:
                dingTalkUserClient.create(DingTalkBeanConvert.toUser(model));
        }
        return new SuccessResponse();
    }
}
