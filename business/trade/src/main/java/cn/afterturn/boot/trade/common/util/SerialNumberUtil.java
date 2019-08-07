package cn.afterturn.boot.trade.common.util;

import cn.afterturn.boot.core.util.DateUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

/**
 * 一些特定的序列号
 *
 * @author jueyue
 */
public class SerialNumberUtil {

    public static String getCompanySerialNo() {
        return "CM" + DateUtil.format(new Date(), "yyMM") + RandomStringUtils.randomNumeric(3);
    }

    public static String getAppIdSerialNo() {
        return "AP" + DateUtil.format(new Date(), "yyMM") + RandomStringUtils.randomNumeric(3);
    }

    public static String getAccountSerialNo() {
        return "AC" + DateUtil.format(new Date(), "yyMMddHHmm") + RandomStringUtils.randomNumeric(6);
    }


    public static String getTradeSerialNo() {
        return "TR" + DateUtil.format(new Date(), "yyMMddHHmm") + RandomStringUtils.randomNumeric(6);
    }
}
