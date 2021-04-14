package com.wupaas.boot.trade.controller;

import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.common.util.ToolUtil;
import com.wupaas.boot.trade.common.exception.TradeBizException;
import com.wupaas.boot.trade.common.exception.TradeBizExceptionEnum;
import com.wupaas.boot.trade.common.util.SerialNumberUtil;
import com.wupaas.boot.trade.model.AccountAppModel;
import com.wupaas.boot.trade.service.IAccountAppService;
import com.wupaas.boot.trade.service.IAccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 * 应用
 *
 * @author
 * @Date
 */
@Api("App交易")
@Controller
@RequestMapping("/accountapp")
public class AccountAppController extends BaseController<IAccountAppService, AccountAppModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAppController.class);

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private IAccountInfoService accountInfoService;


    @ApiOperation(value = "转账", httpMethod = "POST")
    @RequestMapping(value = "/transfer")
    @ResponseBody
    public Object transfer(AccountAppModel model, double money, int type) {
        if (ToolUtil.isOneEmpty(model.getId())) {
            throw new TradeBizException(TradeBizExceptionEnum.REQUEST_NULL);
        }
        switch (type) {
            //直接充值
            case 2:
                model = accountAppService.getById(model.getId());
                String tradeNo = SerialNumberUtil.getTradeSerialNo();
                // 直接充值到APP
                accountInfoService.recharge(tradeNo, model.getAccountNo(), (int) (money * 100));
                break;

        }
        return SUCCESS_RESPONSE;
    }
}
