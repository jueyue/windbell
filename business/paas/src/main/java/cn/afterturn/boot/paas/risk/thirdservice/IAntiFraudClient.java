package cn.afterturn.boot.paas.risk.thirdservice;


import java.util.Map;

/**
 * @author jueyue
 */
public interface IAntiFraudClient {
    /**
     * 反欺诈
     * @param phone
     * @param name
     * @param idcard
     * @return
     */
    Map<String, String> antiFraud(String phone, String name, String idcard);

}
