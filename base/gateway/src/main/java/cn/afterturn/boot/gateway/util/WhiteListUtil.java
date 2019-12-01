package cn.afterturn.boot.gateway.util;

/**
 * @author by jueyue on 19-7-4.
 */
public class WhiteListUtil {


    public static boolean isWhiteList(String path) {
        if (path.startsWith("/userAuth/login")) {
            return true;
        }
        if (path.startsWith("/notice/sendVerificationCode")) {
            return true;
        }
        if (path.startsWith("/oauthUser/register")) {
            return true;
        }
        return false;
    }

    public static boolean isLogout(String path) {
        if (path.startsWith("/userAuth/logout")) {
            return true;
        }
        return false;
    }
}
