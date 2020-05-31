package cn.afterturn.boot.paas.im.controller;

import cn.afterturn.boot.bussiness.request.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import cn.afterturn.boot.paas.common.enums.PaasEnum;
import cn.afterturn.boot.paas.im.controller.model.PaasUserRequestModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.clients.IDingTalkUserClient;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.service.DingTalkBeanConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author by jueyue on 19-6-30.
 */
@Api("Paas用户管理")
@RestController
@RequestMapping("/im/user")
public class UserController {

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
