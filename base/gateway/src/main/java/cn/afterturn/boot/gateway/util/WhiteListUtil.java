package cn.afterturn.boot.gateway.util;

/**
 * @author by jueyue on 19-7-4.
 */
public class WhiteListUtil {


    public static boolean isWhiteList(String path) {
        if (path.startsWith("/user/login")) {
            return true;
        }
        return false;
    }
}
