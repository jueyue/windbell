package com.wupaas.boot.trade.controller;

import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.trade.model.AccountInfoModel;
import com.wupaas.boot.trade.service.IAccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 * 账户基本信息
 *
 * @author
 * @Date
 */
@Controller
@RequestMapping("/accountinfo")
public class AccountInfoController extends BaseController<IAccountInfoService, AccountInfoModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoController.class);

    @Autowired
    private IAccountInfoService AccountInfoService;

}