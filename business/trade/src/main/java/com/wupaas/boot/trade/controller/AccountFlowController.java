package com.wupaas.boot.trade.controller;

import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.trade.model.AccountFlowModel;
import com.wupaas.boot.trade.service.IAccountFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 * 账户流水号
 *
 * @author
 * @Date
 */
@Controller
@RequestMapping("/accountflow")
public class AccountFlowController extends BaseController<IAccountFlowService, AccountFlowModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountFlowController.class);

    private String PREFIX = "/biz/";

    @Autowired
    private IAccountFlowService accountFlowService;

}