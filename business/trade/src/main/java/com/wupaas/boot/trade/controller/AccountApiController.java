package com.wupaas.boot.trade.controller;

import com.wupaas.boot.core.business.response.ErrorResponse;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.wupaas.boot.trade.common.exception.TradeBizException;
import com.wupaas.boot.trade.model.*;
import com.wupaas.boot.trade.model.*;
import com.wupaas.boot.trade.model.enums.InternalAccountEnum;
import com.wupaas.boot.trade.service.IAccountAppService;
import com.wupaas.boot.trade.service.IAccountFlowService;
import com.wupaas.boot.trade.service.IAccountInfoService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API  接口
 *
 * @author by jueyue on 18-5-26.
 */
@Api("交易API")
@Controller
@RestController("/account")
public class AccountApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApiController.class);

    @Autowired
    private IAccountInfoService accountInfoService;
    @Autowired
    private IAccountFlowService accountFlowService;
    @Autowired
    private IAccountAppService  accountAppService;

    @ApiOperation(value = "开户", httpMethod = "POST")
    @RequestMapping(value = "/open", method = {RequestMethod.POST})
    public Response<AccountInfoModel> openAccount(@ApiParam("AppId") @RequestParam String appid,
                                                  @ApiParam("账户ID") @RequestParam String name,
                                                  @ApiParam("用户名称") @RequestParam String userName,
                                                  @ApiParam("用户手机号") @RequestParam String userPhone) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(name, "账户名称 不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setName(name);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account != null) {
                return new SuccessResponse<>(account);
                //return new ErrorResponse(-102, "账户已经存在");
            }
            model = accountInfoService.addAccount(appid, name, userName, userPhone);
            return new SuccessResponse(model);
        } catch (IllegalArgumentException e) {
            LOGGER.error("账户新增异常参数: AppId {},账户ID {},用户名称 {},用户手机号 {}", appid, name, userName, userPhone);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("账户新增异常参数: AppId {},账户ID {},用户名称 {},用户手机号 {}", appid, name, userName, userPhone);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("账户新增异常参数: AppId {},账户ID {},用户名称 {},用户手机号 {}", appid, name, userName, userPhone);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }


    @ApiOperation(value = "账户查询", httpMethod = "GET")
    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    public Response<AccountInfoModel> info(@ApiParam("accountNo") @RequestParam String accountNo) {
        try {
            AccountInfoModel model = new AccountInfoModel();
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account != null) {
                AccountInfoModel result = new AccountInfoModel();
                result.setAccountNo(accountNo);
                result.setName(account.getName());
                result.setOpenTime(account.getOpenTime());
                result.setUserPhone(account.getUserPhone());
                result.setUserName(account.getUserName());
                return new SuccessResponse(result);

            }
            return new ErrorResponse(-102, "账户不存在");
        } catch (Exception e) {
            LOGGER.error("账户查询异常参数: 账户号 {}", accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }


    @ApiOperation(value = "余额查询", httpMethod = "GET")
    @RequestMapping(value = "/balance", method = {RequestMethod.GET})
    public Response<AccountInfoModel> balance(@ApiParam("accountNo") @RequestParam String accountNo) {
        try {
            AccountInfoModel model = new AccountInfoModel();
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account != null) {
                AccountInfoModel result = new AccountInfoModel();
                result.setAccountNo(accountNo);
                result.setBalance(account.getBalance());
                result.setFrozenBalance(account.getFrozenBalance());
                return new SuccessResponse(result);

            }
            return new ErrorResponse(-102, "账户不存在");
        } catch (Exception e) {
            LOGGER.error("账户查询异常参数: 账户号 {}", accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "账户修改", httpMethod = "POST")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Response<AccountInfoModel> updateAccount(@ApiParam("AppId") @RequestParam String appid,
                                                    @ApiParam("账户") @RequestParam String accountNo,
                                                    @ApiParam("用户名称") @RequestParam String userName,
                                                    @ApiParam("用户手机号") @RequestParam String userPhone) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "账户名称 不允许为空");
            Assert.notNull(userName, "名称 不允许为空");
            Assert.notNull(userPhone, "手机号 不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account == null) {
                return new ErrorResponse(-102, "账户不存在");
            }
            account.setUserName(userName);
            account.setUserPhone(userPhone);
            //不修改余额---防止并发异常
            account.setBalance(null);
            accountInfoService.updateById(account);
            return new SuccessResponse(account);
        } catch (IllegalArgumentException e) {
            LOGGER.error("账户修改异常参数: AppId {},用户名称 {},用户手机号 {}", appid, userName, userPhone);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("账户修改异常参数: AppId {},用户名称 {},用户手机号 {},错误信息 {}", appid, userName, userPhone, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("账户修改异常参数: AppId {},用户名称 {},用户手机号 {}", appid, userName, userPhone);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "APP充值", httpMethod = "POST")
    @RequestMapping(value = "/appRecharge", method = {RequestMethod.POST})
    public Response<Integer> appRecharge(@ApiParam("AppId") @RequestParam String appid,
                                         @ApiParam("订单号") @RequestParam String tradeNo,
                                         @ApiParam("充值类型(自定义仅显示)") @RequestParam(required = false) String type,
                                         @ApiParam("充值金额") @RequestParam Long money) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            checkTradeNo(appid, tradeNo);
            money = accountInfoService.transfer(appid + "_" + tradeNo, InternalAccountEnum.ALL_MONEY, app.getAccountNo(), money.intValue(), 1002, 2001, type);
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}", appid, tradeNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}, 错误信息 {}", appid, tradeNo, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}", appid, tradeNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }


    @ApiOperation(value = "延迟充值", httpMethod = "POST")
    @RequestMapping(value = "/delayRecharge", method = {RequestMethod.POST})
    public Response<Integer> delayRecharge(@ApiParam("AppId") @RequestParam String appid,
                                           @ApiParam("订单号") @RequestParam String tradeNo,
                                           @ApiParam("充值账户") @RequestParam String accountNo,
                                           @ApiParam("交易时间 yyyyMMddHHmmss") @RequestParam String tradeTime,
                                           @ApiParam("充值类型(自定义仅显示)") @RequestParam(required = false) String type,
                                           @ApiParam("充值金额") @RequestParam Long money) {
        try {
            Assert.hasLength(appid, "APPId 不允许为空");
            Assert.hasLength(tradeNo, "订单号 不允许为空");
            Assert.hasLength(accountNo, "账户 不允许为空");
            Assert.hasLength(tradeTime, "交易时间 不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            checkTradeNo(appid, tradeNo);
            money = accountInfoService.transferToFrozen(appid + "_" + tradeNo, app.getAccountNo(), accountNo, money.intValue(), 2002, 3003, type, tradeTime);
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}", appid, tradeNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}, 错误信息 {}", appid, tradeNo, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("APP充值异常参数: AppId {},订单号 {},充值金额 {}", appid, tradeNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "充值", httpMethod = "POST")
    @RequestMapping(value = "/recharge", method = {RequestMethod.POST})
    public Response<Integer> recharge(@ApiParam("AppId") @RequestParam String appid,
                                      @ApiParam("订单号") @RequestParam String tradeNo,
                                      @ApiParam("充值账户") @RequestParam String accountNo,
                                      @ApiParam("充值类型(自定义仅显示)") @RequestParam(required = false) String type,
                                      @ApiParam("充值金额") @RequestParam Long money) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "账户不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            checkTradeNo(appid, tradeNo);
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (app == null) {
                return new ErrorResponse(-102, "账户不存在存在");
            }
            money = accountInfoService.transfer(appid + "_" + tradeNo, app.getAccountNo(), accountNo, money.intValue(), 2002, 3001, type);
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("充值异常参数: AppId {},订单号 {},充值账户 {},充值金额 {}", appid, tradeNo, accountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("充值异常参数: AppId {},订单号 {},充值账户 {},充值金额 {}, 错误信息 {}", appid, tradeNo, accountNo, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("充值异常参数: AppId {},订单号 {},充值账户 {},充值金额 {}", appid, tradeNo, accountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "余额查询", httpMethod = "POST")
    @RequestMapping(value = "/balance", method = {RequestMethod.POST})
    public Response<Integer> recharge(@ApiParam("AppId") @RequestParam String appid,
                                      @ApiParam("账户") @RequestParam String accountNo) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "账户不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account == null) {
                return new ErrorResponse(-102, "账户不存在存在");
            }
            return new SuccessResponse(account.getBalance());
        } catch (IllegalArgumentException e) {
            LOGGER.error("余额查询异常参数: AppId {},账户 {},充值金额 {}", appid, accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("余额查询异常参数: AppId {},账户 {},充值金额 {} 错误信息 {}", appid, accountNo, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("余额查询异常参数: AppId {},账户 {},充值金额 {}", appid, accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "批量转账", httpMethod = "POST")
    @RequestMapping(value = "/batchTransfer", method = {RequestMethod.POST})
    public Response<Integer> batchTransfer(@ApiParam("AppId") @RequestParam String appid,
                                           @ApiParam("订单号") @RequestParam String sourceId,
                                           @ApiParam("批量转账数据,tradeNo,fromAccount,toAccountNo,money DEMO " +
                                                   "[{fromAccount:'AC1806170030124163',toAccountNo:'AC1806120949542975',tradeNo:'11111111111111111_1',money:12}, " +
                                                   "{fromAccount:'AC1806190945512163',toAccountNo:'AC1806261105723994',tradeNo:'11111111111111111_2',money:24}]") @RequestBody List<Map<String, String>> list) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(sourceId, "订单号不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            accountInfoService.batchTransfer(appid, sourceId, list, 3012, 3011);
            return new SuccessResponse();
        } catch (IllegalArgumentException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {}", appid, sourceId);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {} 错误信息 {}", appid, sourceId, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {}", appid, sourceId);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "转账", httpMethod = "POST")
    @RequestMapping(value = "/transfer", method = {RequestMethod.POST})
    public Response<Integer> transfer(@ApiParam("AppId") @RequestParam String appid,
                                      @ApiParam("订单号") @RequestParam String tradeNo,
                                      @ApiParam("付款账户") @RequestParam String fromAccount,
                                      @ApiParam("收款账户") @RequestParam String toAccountNo,
                                      @ApiParam("转账金额") @RequestParam Long money) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(fromAccount, "付款账户不允许为空");
            Assert.notNull(toAccountNo, "收款账户不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(fromAccount);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (account == null) {
                return new ErrorResponse(-102, "付款账户不存在存在");
            }
            model.setAccountNo(toAccountNo);
            account = accountInfoService.selectOne(model);
            if (account == null) {
                return new ErrorResponse(-102, "收款账户不存在存在");
            }
            money = accountInfoService.transfer(appid + "_" + tradeNo, fromAccount, toAccountNo, money.intValue(), 3012, 3011, null);
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {},转出账户 {},转入账户 {},转账金额 {}", appid, tradeNo, fromAccount, toAccountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {},转出账户 {},转入账户 {},转账金额 {} 错误信息 {}", appid, tradeNo, fromAccount, toAccountNo, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("转账异常参数: AppId {},订单号 {},转出账户 {},转入账户 {},转账金额 {}", appid, tradeNo, fromAccount, toAccountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "提现", httpMethod = "POST")
    @RequestMapping(value = "/forward", method = {RequestMethod.POST})
    public Response<Integer> forward(@ApiParam("AppId") @RequestParam String appid,
                                     @ApiParam("付款账户") @RequestParam String accountNo,
                                     @ApiParam("订单号") @RequestParam String tradeNo,
                                     @ApiParam("结算信息{toCardHolder\tString\t不可以\t姓名\n" +
                                             "toCardIdentity\tString\t不可以\t身份证\n" +
                                             "toCardMobile\tString\t不可以\t手机号\n" +
                                             "toCardNo\tString\t不可以\t卡号\n" +
                                             "toCardSubBankCode\tString\t不可以\t联行号\n" +
                                             "toCardSubBankName\tString\t不可以\t联行名称\n" +
                                             "toCardType\tString\t不可以\t卡类型}") @RequestParam String settlement,
                                     @ApiParam("提现金额") @RequestParam Long money) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "付款账户不允许为空");
            Assert.notNull(settlement, "结算不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (app == null) {
                return new ErrorResponse(-102, "付款账户不存在存在");
            }
            if (account.getIsCash() == 2) {
                return new ErrorResponse(-102, "付款账户不允许提现");
            }
            money = accountInfoService.forward(appid, appid + "_" + tradeNo, accountNo, JSON.parseObject(settlement, PaymentModel.class), money.intValue());
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {}", appid, tradeNo, accountNo, settlement, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {} 错误信息 {}", appid, tradeNo, accountNo, settlement, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {}", appid, tradeNo, accountNo, settlement, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }


    @ApiOperation(value = "提现带手续费版本", httpMethod = "POST")
    @RequestMapping(value = "/forwardAndFee", method = {RequestMethod.POST})
    public Response<Integer> forwardAndFee(@ApiParam("AppId") @RequestParam String appid,
                                           @ApiParam("付款账户,多个,分隔") @RequestParam String accountNo,
                                           @ApiParam("订单号") @RequestParam String tradeNo,
                                           @ApiParam("结算信息{toCardHolder\tString\t不可以\t姓名\n" +
                                                   "toCardIdentity\tString\t不可以\t身份证\n" +
                                                   "toCardMobile\tString\t不可以\t手机号\n" +
                                                   "toCardNo\tString\t不可以\t卡号\n" +
                                                   "toCardSubBankCode\tString\t不可以\t联行号\n" +
                                                   "toCardSubBankName\tString\t不可以\t联行名称\n" +
                                                   "toCardType\tString\t不可以\t卡类型}") @RequestParam String settlement,
                                           @ApiParam("提现金额") @RequestParam Long money,
                                           @ApiParam("提现手续费") @RequestParam Long fee) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "付款账户不允许为空");
            Assert.notNull(settlement, "结算不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            Assert.isTrue(fee >= 0, "手续费不允许小于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            String[] accountNos = accountNo.split(",");
            for (int i = 0; i < accountNos.length; i++) {
                AccountInfoModel model = new AccountInfoModel();
                model.setAppid(appid);
                model.setAccountNo(accountNos[i]);
                AccountInfoModel account = accountInfoService.selectOne(model);
                if (app == null) {
                    return new ErrorResponse(-102, "付款账户不存在存在");
                }
                if (account.getIsCash() == 2) {
                    return new ErrorResponse(-102, "付款账户不允许提现");
                }
            }
            money = accountInfoService.forward(appid, appid + "_" + tradeNo, accountNos, JSON.parseObject(settlement, PaymentModel.class), money.intValue(), fee.intValue());
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {}", appid, tradeNo, accountNo, settlement, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {} 错误信息 {}", appid, tradeNo, accountNo, settlement, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("提现异常参数: AppId {},订单号 {},账户 {},结算 {},提现金额 {}", appid, tradeNo, accountNo, settlement, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "退款", httpMethod = "POST")
    @RequestMapping(value = "/refund", method = {RequestMethod.POST})
    public Response<Integer> refund(@ApiParam("AppId") @RequestParam String appid,
                                    @ApiParam("收款账户,多个,分隔,与原订单一致") @RequestParam String accountNo,
                                    @ApiParam("原订单号") @RequestParam String tradeNo,
                                    @ApiParam("类型(1 带手续费版本提现, 2 不带手续费版本提现)") @RequestParam(defaultValue = "2") int type) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "收款账户不允许为空");
            Assert.notNull(type, "类型不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            String[] accountNos = accountNo.split(",");
            for (int i = 0; i < accountNos.length; i++) {
                AccountInfoModel model = new AccountInfoModel();
                model.setAppid(appid);
                model.setAccountNo(accountNos[i]);
                AccountInfoModel account = accountInfoService.selectOne(model);
                if (app == null) {
                    return new ErrorResponse(-102, "付款账户不存在存在");
                }
            }
            String[] tradeNos = new String[accountNos.length * 2];
            if (accountNos.length == 1 && type == 2) {
                tradeNos[0] = appid + "_" + tradeNo;
            } else {
                for (int i = 0; i < accountNos.length; i++) {
                    tradeNos[i * 2] = appid + "_" + tradeNo + "_" + accountNos[i];
                    tradeNos[i * 2 + 1] = appid + "_" + tradeNo + "_" + accountNos[i] + "_FEE";
                }
            }
            Long money = accountInfoService.refund(tradeNos, accountNos, type, 6002, 6001);
            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("退款异常参数: AppId {},订单号 {},账户 {}", appid, tradeNo, accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("退款异常参数: AppId {},订单号 {},账户 {}, 错误信息 {}", appid, tradeNo, accountNo, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("退款异常参数: AppId {},订单号 {},账户 {}", appid, tradeNo, accountNo);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    @ApiOperation(value = "回调", httpMethod = "POST")
    @RequestMapping(value = "/callback")
    public Map<String, String> callback(TradePushCallBackModel model) {
        Map<String, String> result = new HashMap<>();
        try {
            LOGGER.info("callback data is:" + JSON.toJSONString(model));
            result.put("status", "success");
        } catch (RuntimeException e) {
            LOGGER.error("callback data is:" + JSON.toJSONString(model));
            LOGGER.error(e.getMessage(), e);
            result.put("status", "error");
        }
        return result;
    }


    private void checkTradeNo(String appid, String tradeNo) {
        AccountFlowModel model = new AccountFlowModel();
        model.setTradeNo(appid + "_" + tradeNo);
        if (!CollectionUtils.isEmpty(accountFlowService.selectList(model))) {
            throw new TradeBizException(-102, "订单已存在");
        }
        /*AccountFlowModel update = new AccountFlowModel();
        update.setId(model.getId());
        update.setStatus(2);
        accountFlowService.updateById(update);*/

    }


    @ApiOperation(value = "冻结余额入账", httpMethod = "POST")
    @RequestMapping(value = "/frozenToBal", method = {RequestMethod.POST})
    public Response<Integer> frozenToBal(@ApiParam("AppId") @RequestParam String appid,
                                         @ApiParam("账户") @RequestParam String accountNo,
                                         @ApiParam("订单号") @RequestParam String tradeNo,
                                         @ApiParam("开始时间>=") @RequestParam String startTime,
                                         @ApiParam("结束时间<") @RequestParam String endTime) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(tradeNo, "订单号不允许为空");
            Assert.notNull(startTime, "开始时间不允许为空");
            Assert.notNull(endTime, "结束时间不允许为空");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (app == null) {
                return new ErrorResponse(-102, "付款账户不存在存在");
            }
            checkTradeNo(appid, tradeNo);
            int money = accountInfoService.sysFrozenToBal(appid + "_" + tradeNo, appid, accountNo, startTime, endTime);

            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("冻结余额入账: AppId {},订单号 {},账户 {},开始时间 {},结束时间 {}", appid, tradeNo, accountNo, startTime, endTime);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("冻结余额入账: AppId {},订单号 {},账户 {},开始时间 {},结束时间 {} 错误信息 {}", appid, tradeNo, accountNo, startTime, endTime, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("冻结余额入账: AppId {},订单号 {},账户 {},开始时间 {},结束时间 {}", appid, tradeNo, accountNo, startTime, endTime);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }


    @ApiOperation(value = "冻结/解冻", httpMethod = "POST")
    @RequestMapping(value = "/frozen", method = {RequestMethod.POST})
    public Response<Integer> frozen(@ApiParam("类型(1 冻结,2 解冻, 3 解冻还原)") @RequestParam int type,
                                    @ApiParam("AppId") @RequestParam String appid,
                                    @ApiParam("订单号") @RequestParam String tradeNo,
                                    @ApiParam("源订单号(冻结为空,解冻填冻结的订单)") @RequestParam(required = false) String sourceTradeNo,
                                    @ApiParam("账户") @RequestParam String accountNo,
                                    @ApiParam("金额") @RequestParam Long money) {
        try {
            Assert.notNull(appid, "APPId 不允许为空");
            Assert.notNull(accountNo, "账户不允许为空");
            Assert.notNull(type, "类型不允许为空");
            Assert.isTrue(money > 0, "金额要大于0");
            AccountAppModel app = accountAppService.selectOne(new AccountAppModel(appid));
            if (app == null) {
                return new ErrorResponse(-102, "应用不存在");
            }
            AccountInfoModel model = new AccountInfoModel();
            model.setAppid(appid);
            model.setAccountNo(accountNo);
            AccountInfoModel account = accountInfoService.selectOne(model);
            if (app == null) {
                return new ErrorResponse(-102, "付款账户不存在存在");
            }

            money = sysFrozen(type, sourceTradeNo, appid, tradeNo, accountNo, money);

            return new SuccessResponse(money);
        } catch (IllegalArgumentException e) {
            LOGGER.error("冻结异常参数: AppId {},订单号 {},账户 {},金额 {}", appid, tradeNo, accountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-101, "参数错误");
        } catch (TradeBizException e) {
            LOGGER.error("冻结异常参数: AppId {},订单号 {},账户 {},金额 {} 错误信息 {}", appid, tradeNo, accountNo, money, e.getMessage());
            return new ErrorResponse(e.getCode(), e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("冻结异常参数: AppId {},订单号 {},账户 {},金额 {}", appid, tradeNo, accountNo, money);
            LOGGER.error(e.getMessage(), e);
            return new ErrorResponse(-99, "系统异常");
        }
    }

    /**
     * 同步冻结操作
     *
     * @param type
     * @param sourceTradeNo
     * @param appid
     * @param tradeNo
     * @param accountNo
     * @param money
     * @return
     */
    private synchronized Long sysFrozen(int type, String sourceTradeNo, String appid, String tradeNo, String accountNo, Long money) {
        if (type == 2) {
            Assert.notNull(sourceTradeNo, "源订单号不允许为空");
        }
        if (type == 1) {
            return accountInfoService.transfer(appid + "_" + tradeNo + "_9021", accountNo, InternalAccountEnum.FROZEN, money.intValue(), 9022, 9021, null);
        } else if (type == 2) {
            //检查 源订单是否存在
            AccountFlowModel sourceModel = accountFlowService.selectList(new AccountFlowModel(appid + "_" + sourceTradeNo + "_9021")).get(0);
            // 检查解冻的金额是否超过了原来的金额
            int thawMoney = accountFlowService.getTradeListMoney(appid + "_" + sourceTradeNo + "_9022", 9022);
            // 重新冻结的金额
            int againFrozen = accountFlowService.getTradeListMoney(appid + "_" + sourceTradeNo + "_9021_", 9021);
            if (thawMoney + money - againFrozen > sourceModel.getMoney()) {
                throw new TradeBizException(-101, "解冻金额大于原金额");
            }
            return accountInfoService.transfer(appid + "_" + sourceTradeNo + "_9022" + "_" + tradeNo, InternalAccountEnum.FROZEN, accountNo, money.intValue(), 9022, 9021, null);
        } else if (type == 3) {
            //检查 源订单是否存在
            AccountFlowModel sourceModel = accountFlowService.selectList(new AccountFlowModel(appid + "_" + sourceTradeNo + "_9022" + "_" + tradeNo)).get(0);
            if (sourceModel == null) {
                throw new TradeBizException(-101, "原解冻订单不存在");
            }
            return accountInfoService.transfer(appid + "_" + sourceTradeNo + "_9021" + "_" + tradeNo, accountNo, InternalAccountEnum.FROZEN, money.intValue(), 9022, 9021, null);
        }
        return 0L;
    }


    /**
     * 获取列表
     */
    @ApiOperation(value = "订单查询", httpMethod = "POST")
    @RequestMapping(value = "/list")
    public Page<AccountFlowModel> list(@ApiParam("订单号") @RequestParam(required = false) String tradeNo,
                                       @ApiParam("账户号") @RequestParam String accountNo,
                                       @ApiParam("页数") @RequestParam(defaultValue = "1") int pageNum,
                                       @ApiParam("条数") @RequestParam(defaultValue = "10") int pageSize,
                                       @ApiParam("开始时间(yyy-mm-dd HH:MM:SS)") @RequestParam String tradeTimeSt,
                                       @ApiParam("结束时间(yyy-mm-dd HH:MM:SS)") @RequestParam String tradeTimeEd) {
        Page<AccountFlowModel> page    = new Page(pageNum, pageSize);
        QueryWrapper           wrapper = new QueryWrapper<AccountFlowModel>();
        if (StringUtils.isNotBlank(tradeTimeSt)) {
            wrapper.ge("TRADE_TIME", tradeTimeSt);
        }
        if (StringUtils.isNotBlank(tradeTimeEd)) {
            wrapper.le("TRADE_TIME", tradeTimeEd);
        }
        if (StringUtils.isNotEmpty(tradeNo)) {
            wrapper.like("TRADE_NO", tradeNo);
        }
        AccountFlowModel model = new AccountFlowModel();
        model.setAccountNo(accountNo);
        page.setRecords(accountFlowService.selectPage(page, model, wrapper));
        return page;
    }
}
