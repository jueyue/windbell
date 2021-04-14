package com.wupaas.boot.admin.controller;

import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.wupaas.boot.core.common.monitor.MonitorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.wupaas.boot.admin.common.Constant.ADMIN;

/**
 * 系统相关服务
 *
 * @author jueyue on 20-6-21.
 */

@Api("系统管理")
@RestController
@RequestMapping(ADMIN + "/system")
public class SystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @ApiOperation(value = "系统监控")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public Response<Map> monitor() {
        return new SuccessResponse<>(MonitorUtil.getCurrentServer());
    }

}
