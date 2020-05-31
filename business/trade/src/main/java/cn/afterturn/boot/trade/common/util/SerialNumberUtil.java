package cn.afterturn.boot.trade.common.util;

import cn.afterturn.boot.core.util.DateUtil;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Date;

/**
 * 一些特定的序列号
 *
 * @author jueyue
 */
public class SerialNumberUtil {

    public static String getCompanySerialNo() {
        return "CM" + DateUtil.format(new Date(), "yyMM") + new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(3);
    }

    public static String getAppIdSerialNo() {
        return "AP" + DateUtil.format(new Date(), "yyMM") + new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(3);
    }

    public static String getAccountSerialNo() {
        return "AC" + DateUtil.format(new Date(), "yyMMddHHmm") + new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(6);
    }


    public static String getTradeSerialNo() {
        return "TR" + DateUtil.format(new Date(), "yyMMddHHmm") + new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(6);
    }
}
