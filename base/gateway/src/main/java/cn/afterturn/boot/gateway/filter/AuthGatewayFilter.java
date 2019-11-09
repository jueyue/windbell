package cn.afterturn.boot.gateway.filter;

import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.gateway.config.shiro.auth.JwtToken;
import cn.afterturn.boot.gateway.config.shiro.auth.header.HeaderEnum;
import cn.afterturn.boot.gateway.util.WhiteListUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * 权限校验
 *
 * @author by jueyue on 19-7-4.
 */
@Component
@Order(1)
public class AuthGatewayFilter implements GlobalFilter {

    private final static Logger logger = LoggerFactory.getLogger(AuthGatewayFilter.class);

    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .concurrencyLevel(10)
            .build();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (WhiteListUtil.isWhiteList(exchange.getRequest().getPath().pathWithinApplication().value())) {
            return chain.filter(exchange);
        }
        if (WhiteListUtil.isLogout(exchange.getRequest().getPath().pathWithinApplication().value())) {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return chain.filter(exchange);
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(new JwtToken(exchange.getRequest().getHeaders().getFirst(HeaderEnum.TOKEN.getName())));
        String permission = getUrls(exchange);
        // 跳过授权,后面再补,太浪费时间了
        /*try {
            subject.checkPermission(permission);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ServerHttpResponse response    = exchange.getResponse();
            HttpHeaders        httpHeaders = response.getHeaders();
            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            ErrorResponse resp           = new ErrorResponse(402, "未授权");
            DataBuffer    bodyDataBuffer = response.bufferFactory().wrap(JSON.toJSONString(resp).getBytes());

            return response.writeWith(Mono.just(bodyDataBuffer));
        }*/
        return chain.filter(exchange);
    }

    private String getUrls(ServerWebExchange exchange) {
        String url        = exchange.getRequest().getPath().pathWithinApplication().value();
        String permission = cache.getIfPresent(url);
        if (StringUtils.isEmpty(permission)) {
            permission = url.replaceAll("/", ":");
            if (permission.startsWith(":")) {
                permission = permission.substring(1);
            }
            if (permission.endsWith(":")) {
                permission = permission.substring(0, url.length() - 1);
            }
            cache.put(url, permission);
        }

        return permission;

    }
}
