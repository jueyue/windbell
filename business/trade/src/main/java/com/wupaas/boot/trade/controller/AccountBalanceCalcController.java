
package com.wupaas.boot.trade.controller;

import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.trade.model.AccountBalanceCalcModel;
import com.wupaas.boot.trade.service.IAccountBalanceCalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 总分平衡控制器
 * 总分平衡
 *
 * @author JueYue
 * @Date 2018-09-12 19:47:54
 */
@Controller
@RequestMapping("/accountbalancecalc")
public class AccountBalanceCalcController extends BaseController<IAccountBalanceCalcService, AccountBalanceCalcModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountBalanceCalcController.class);

    @Autowired
    private IAccountBalanceCalcService accountBalanceCalcService;

}
