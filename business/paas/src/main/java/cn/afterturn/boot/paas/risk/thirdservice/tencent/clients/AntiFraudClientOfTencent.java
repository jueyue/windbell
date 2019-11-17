package cn.afterturn.boot.paas.risk.thirdservice.tencent.clients;

import cn.afterturn.boot.core.util.HttpsUtil;
import cn.afterturn.boot.paas.risk.thirdservice.IAntiFraudClient;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 天御服务API工具类
 *
 * @author jueyue
 */
@Slf4j
@Component("antiFraudClientOfTencent")
public class AntiFraudClientOfTencent implements IAntiFraudClient {

    private static final String URL = "csec.api.qcloud.com/v2/index.php";

    @Value("${risk.tencent.fraud.secretId}")
    private String secretId;
    @Value("${risk.tencent.fraud.secretKey}")
    private String secretKey;

    /**
     * @param phone
     * @param name
     * @param idcard
     * @return {"code":0,"codeDesc":"Success","found":1,"idFound":1,"message":"No Error","riskInfo":[{"riskCode":5,"riskCodeValue":2}],"riskScore":49}
     */
    @Override
    public Map<String, String> antiFraud(String phone, String name, String idcard) {
        Map<String, String> args = new TreeMap();
        args.put("idNumber", idcard);
        args.put("phoneNumber", "0086-" + phone);
        args.put("name", name);
        Map<String, String> result = new HashMap<>();
        try {
            String       url    = makeURL("GET", "AntiFraud", "gz", secretId, secretKey, args, "utf-8");
            String       res    = HttpsUtil.get(url, "");
            TianYuResult tianyu = JSON.parseObject(res, TianYuResult.class);
            if (tianyu.code == 0 && tianyu.found == 1) {
                result.put("found", "1");
                result.put("score", tianyu.riskScore + "");
                result.put("info", JSON.toJSONString(tianyu.riskInfo));
            } else {
                result.put("found", "-1");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.put("found", "99");
        }

        return result;
    }

    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    private String encode(byte[] bstr) {
        String sp = System.getProperty("line.separator");
        return (new BASE64Encoder().encode(bstr)).replaceAll(sp, "");
    }

    public String hmacSHA1(String key, String text, String charset) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key.getBytes(charset), "HmacSHA1"));
        return encode(mac.doFinal(text.getBytes(charset)));
    }

    private String makeQueryString(Map<String, String> args, String charset) throws UnsupportedEncodingException {
        String url = "";

        for (Map.Entry<String, String> entry : args.entrySet()) {
            url += entry.getKey() + "=" + (charset == null ? entry.getValue() : URLEncoder.encode(entry.getValue(), charset)) + "&";
        }
        return url.substring(0, url.length() - 1);
    }

    public String makeURL(
            String method,
            String action,
            String region,
            String secretId,
            String secretKey,
            Map<String, String> args,
            String charset)
            throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SortedMap<String, String> arguments = new TreeMap<String, String>();

        /* Sort all parameters, then calculate signature */
        arguments.putAll(args);
        arguments.put("Nonce", String.valueOf((int) (Math.random() * 0x7fffffff)));
        arguments.put("Action", action);
        arguments.put("Region", region);
        arguments.put("SecretId", secretId);
        arguments.put("Timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        arguments.put("Signature", hmacSHA1(secretKey, String.format("%s%s?%s", method, URL, makeQueryString(arguments, null)), charset));

        /* Assemble final request URL */
        return String.format("https://%s?%s", URL, makeQueryString(arguments, charset));
    }

    @Data
    public static class TianYuResult {
        /**
         * 公共错误码 0：表示成功 其他值：表示失败 详见 错误码 页面中的 公共错误码 相关内容
         */
        private int          code;
        /**
         * 业务侧错误码 成功时返回 Success 错误时返回具体业务错误原因
         */
        private String       codeDesc;
        /**
         * 表示该条记录能否查到 1：能查到 -1：查不到
         */
        private int          found;
        /**
         * 表示该条记录中的身份证能否查到 1：能查到 -1：查不到
         */
        private int          idFound;
        /**
         * 模块错误信息描述，与接口相关。
         */
        private String       message;
        /**
         * 扩展字段，对风险类型的说明； riskScore 为 0 ：无此字段
         */
        private List<Object> riskInfo;
        /**
         * 0-100：欺诈分值 值越高欺诈可能性越大
         */
        private int          riskScore;
    }

}


