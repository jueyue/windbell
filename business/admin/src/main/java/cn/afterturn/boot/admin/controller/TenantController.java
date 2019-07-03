package cn.afterturn.boot.admin.controller;

import cn.afterturn.boot.admin.model.TenantModel;
import cn.afterturn.boot.admin.service.ITenantService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.admin.ITenantFacade;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 控制器
 *
 * @author
 * @Date 2018-09-03 23:21:00
 */
@Api("")
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController<ITenantService, TenantModel> implements ITenantFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @Autowired
    private ITenantService tenantService;


    @Override
    protected Response handlerCreate(TenantModel model) {
        return SUCCESS_RESPONSE;
    }



}