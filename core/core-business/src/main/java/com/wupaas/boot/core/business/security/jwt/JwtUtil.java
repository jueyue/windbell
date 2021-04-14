package com.wupaas.boot.core.business.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wupaas.boot.core.business.context.EnvironmentContextHolder;

import java.util.Date;

/**
 * jwt 工具
 *
 * @author jueyue on 21-2-19.
 */
public class JwtUtil {

    private static String SECRET = "jwt.secret";
    private static String EXPIRATION = "jwt.expiration";


    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static int verify(String token) {
        try {
            String userName = getUserName(token);
            Algorithm algorithm = Algorithm.HMAC256(EnvironmentContextHolder.getVal(SECRET));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getExpiresAt().compareTo(new Date()) == 1 ? 0 : 4;
        } catch (TokenExpiredException e) {
            return 1;
        } catch (Exception exception) {
            return 2;
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static int verify(String token, String secret) {
        try {
            String userName = getUserName(token);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getExpiresAt().compareTo(new Date()) == 1 ? 0 : 4;
        } catch (TokenExpiredException e) {
            return 1;
        } catch (Exception exception) {
            return 2;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        Date date = new Date(System.currentTimeMillis() + EnvironmentContextHolder.getLongVal(EXPIRATION));
        Algorithm algorithm = Algorithm.HMAC256(EnvironmentContextHolder.getVal(SECRET));
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 生成签名
     *
     * @param username 用户名
     * @param webType  渠道
     * @return 加密的token
     */
    public static String createToken(String username, String webType) {
        Date date = new Date(System.currentTimeMillis() + EnvironmentContextHolder.getLongVal(EXPIRATION));
        Algorithm algorithm = Algorithm.HMAC256(EnvironmentContextHolder.getVal(SECRET));
        return JWT.create()
                .withClaim("username", username)
                .withClaim("webType", webType)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 生成签名
     *
     * @param username   用户名
     * @param secret     加密秘钥
     * @param expiration 过期时间
     * @return 加密的token
     */
    public static String createToken(String username, String secret, long expiration) {
        Date date = new Date(System.currentTimeMillis() + expiration);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
