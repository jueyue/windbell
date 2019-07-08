package cn.afterturn.boot.paas.im.controller;

import cn.afterturn.boot.bussiness.request.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.facade.paas.im.IPaasUserFacade;
import cn.afterturn.boot.facade.paas.im.model.PaasUserRequestModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.WeiXinServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author by jueyue on 19-6-30.
 */
@Api("Paas用户管理")
@RestController
@RequestMapping("/im/user")
public class UserController implements IPaasUserFacade {

    @Autowired
    private WeiXinServiceImpl weiXinService;

    @Override
    public Response<Page<PaasUserRequestModel>> list(RequestParams<PaasUserRequestModel> params) {
        return null;
    }

    @Override
    public Response create(@Valid PaasUserRequestModel model) {
        //weiXinService.getToken(model.getTenantId(),model.getAppCode());
        return new SuccessResponse();
    }

    @Override
    public Response delete(String id) {
        return null;
    }

    @Override
    public Response deleteBatchIds(List<String> ids) {
        return null;
    }

    @Override
    public Response update(PaasUserRequestModel model) {
        return null;
    }

    @Override
    public Response<PaasUserRequestModel> detail(String id) {
        return null;
    }

    @Override
    public Response<PaasUserRequestModel> detailByObj(PaasUserRequestModel model) {
        return null;
    }
}
