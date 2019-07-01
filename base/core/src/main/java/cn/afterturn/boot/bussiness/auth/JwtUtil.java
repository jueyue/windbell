package cn.afterturn.boot.bussiness.auth;

import cn.afterturn.boot.bussiness.auth.header.HeaderEnum;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * jwt 认证工具类
 *
 * @author by jueyue on 19-1-28.
 */
public class JwtUtil {
    /**
     * 过期时间5分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userId, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(HeaderEnum.USER.getName(), userId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(HeaderEnum.USER.getName()).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,30min后过期
     *
     * @param userId   用户名
     * @param roleId   角色
     * @param userName 用户名
     * @param secret   校验参数
     * @return 加密的token
     */
    public static String sign(String userId, String roleId, String userName, String secret) {
        try {
            Date      date      = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim(HeaderEnum.USER.getName(), userId)
                    .withClaim(HeaderEnum.USER_NAME.getName(), userName)
                    .withClaim(HeaderEnum.ROLE.getName(), roleId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static String parseJwtPayload(String jwt) {
        return null;
    }

    public static Map<String, Object> readValue(String substring) {
        return null;
    }

    public static Set<String> split(String roles) {
        return null;
    }
}
