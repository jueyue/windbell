package cn.afterturn.boot.trade.controller;

import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.core.util.ToolUtil;
import cn.afterturn.boot.trade.common.exception.BizException;
import cn.afterturn.boot.trade.common.exception.BizExceptionEnum;
import cn.afterturn.boot.trade.common.util.SerialNumberUtil;
import cn.afterturn.boot.trade.model.AccountAppModel;
import cn.afterturn.boot.trade.service.IAccountAppService;
import cn.afterturn.boot.trade.service.IAccountInfoService;
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
@Controller
@RequestMapping("/accountapp")
public class AccountAppController extends BaseController<IAccountAppService, AccountAppModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAppController.class);

    private String PREFIX = "/biz/";

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private IAccountInfoService accountInfoService;


    @RequestMapping(value = "/transfer")
    @ResponseBody
    public Object transfer(AccountAppModel model, double money, int type) {
        if (ToolUtil.isOneEmpty(model.getId())) {
            throw new BizException(BizExceptionEnum.REQUEST_NULL);
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