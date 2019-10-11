package cn.afterturn.boot.paas.common.context;

import cn.afterturn.boot.core.cache.CacheKey;
import cn.afterturn.boot.core.cache.RedisKit;
import cn.afterturn.boot.paas.im.service.ITokenService;

import java.util.HashMap;
import java.util.Map;

/**
 * 三方服务临时缓存数据,方便获取token
 *
 * @author by jueyue on 19-7-15.
 */
public class ThirdServiceContext {

    public static final String TENANT_ID = "tenantId";

    public static final String APP_CODE = "appCode";

    public static final String APP_TYPE = "appType";

    private static ThreadLocal<Map> mapThreadLocal = new ThreadLocal<Map>();

    public static void put(String key, String val) {
        Map<String, String> map = mapThreadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            mapThreadLocal.set(map);
        }
        map.put(key, val);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken(ITokenService tokenService) {
        Map<String, String> map      = mapThreadLocal.get();
        String              tenantId = map.get(TENANT_ID);
        String              appCode  = map.get(APP_CODE);
        String              appType  = map.get(APP_TYPE);
        String              token    = RedisKit.get(CacheKey.get("thirdservice").append("token").append(appType).append(tenantId).append(appCode).toString());
        if (token == null) {
            token = tokenService.getToken(tenantId, appType, appCode);
            RedisKit.put(CacheKey.get("thirdservice").append("token").append(appType).append(tenantId).append(appCode).toString(), token, 7000);
        }
        return token;
    }


}
