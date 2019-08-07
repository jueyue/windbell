package cn.afterturn.boot.trade.model;

import lombok.Data;

/**
 * 回调对象
 *
 * @author JueYue on 2017/11/1.
 */
@Data
public class TradePushCallBackModel {

    private String qdcrmUserId;
    private String tradeNo;
    private String orderNo;
    private String status;
    private String amount;
    private String settleAmount;
    private String profit;
    private String fee;
    private String payTime;
    private String notifyUrl;
    private String agentMerchant;

}
