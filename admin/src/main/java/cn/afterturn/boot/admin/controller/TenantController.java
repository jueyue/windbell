package cn.afterturn.boot.admin.controller;

import cn.afterturn.boot.admin.model.TenantModel;
import cn.afterturn.boot.admin.service.ILinkTenantProductService;
import cn.afterturn.boot.admin.service.ITenantService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 控制器
 *
 * @author
 * @Date 2018-09-03 23:21:00
 */
@Api("")
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController<ITenantService, TenantModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @Autowired
    private ITenantService            tenantService;
    @Autowired
    private ILinkTenantProductService linkTenantProductService;

    @Override
    protected Response handlerCreate(TenantModel model) {
        return SUCCESS_RESPONSE;
    }

    @ApiOperation(value = "查询用户产品")
    @GetMapping(value = "/getTenantByTenantId/{tenantId}")
    public Response<TenantModel> getTenantByTenantId(@PathVariable String tenantId) {
        TenantModel  model  = tenantService.getOne(new TenantModel(tenantId));
        return new SuccessResponse<>(model);
    }

    @ApiOperation(value = "新增产品")
    @RequestMapping(value = "/addProduct/{id}/{product}/{endDate}", method = RequestMethod.GET)
    public Response addProduct(@ApiParam("租户ID") @PathVariable String id,
                               @ApiParam("产品ID") @PathVariable String product,
                               @ApiParam("到期日期yyy-mm-dd") @PathVariable String endDate) {
        TenantModel model = tenantService.getById(id);
        linkTenantProductService.addOrUpdateProduct(model.getTenantId(), product, endDate);
        return SUCCESS_RESPONSE;
    }

    @ApiOperation(value = "Paas数据初始化")
    @RequestMapping(value = "/paasInit/{id}", method = RequestMethod.POST)
    public Response paasInit(@PathVariable String id) {
        tenantService.paasInit(id);
        return null;
    }
}