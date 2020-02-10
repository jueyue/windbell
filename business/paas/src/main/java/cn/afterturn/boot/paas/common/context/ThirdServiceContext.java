package cn.afterturn.boot.paas.common.context;

import cn.afterturn.boot.core.cache.CacheKey;
import cn.afterturn.boot.core.cache.RedisKit;
import cn.afterturn.boot.paas.common.enums.PaasEnum;
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

    private static ThreadLocal<Map> mapThreadLocal = new ThreadLocal<>();

    public static void put(String key, Object val) {
        Map<String, Object> map = mapThreadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            mapThreadLocal.set(map);
        }
        map.put(key, val);
    }

    public static String getAppType(String tenantId) {
        if (mapThreadLocal.get() != null && mapThreadLocal.get().get(APP_TYPE) != null) {
            return mapThreadLocal.get().get(APP_TYPE).toString();
        } else {
            String appType = RedisKit.get(CacheKey.get("admin").append("tenant").append(tenantId).append(APP_TYPE).toString()).toString();
            put(APP_TYPE, appType);
            return appType;
        }
    }

    public static PaasEnum getAppEnum(String tenantId) {
        return PaasEnum.to(getAppType(tenantId));
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken(ITokenService tokenService) {
        Map<String, Object> map      = mapThreadLocal.get();
        String              tenantId = map.get(TENANT_ID).toString();
        String              appCode  = map.get(APP_CODE).toString();
        String              appType  = getAppType(tenantId);
        String              token    = RedisKit.get(CacheKey.get("thirdservice").append("token").append(appType).append(tenantId).append(appCode).toString()).toString();
        if (token == null) {
            token = tokenService.getToken(tenantId, appType, appCode);
            RedisKit.put(CacheKey.get("thirdservice").append("token").append(appType).append(tenantId).append(appCode).toString(), token, 7000);
        }
        return token;
    }


}
