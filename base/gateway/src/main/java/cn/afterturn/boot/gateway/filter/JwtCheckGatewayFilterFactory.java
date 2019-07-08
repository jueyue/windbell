package cn.afterturn.boot.gateway.filter;

import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.gateway.config.shiro.auth.JwtUtil;
import cn.afterturn.boot.gateway.config.shiro.auth.header.HeaderEnum;
import cn.afterturn.boot.gateway.util.WhiteListUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * token校验
 *
 * @author by jueyue on 19-1-30.
 */
@Component
@Order(0)
public class JwtCheckGatewayFilterFactory implements GlobalFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtCheckGatewayFilterFactory() {
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (WhiteListUtil.isWhiteList(exchange.getRequest().getPath().pathWithinApplication().value())) {
            return chain.filter(exchange);
        }
        String jwtToken = exchange.getRequest().getHeaders().getFirst(HeaderEnum.TOKEN.getName());
        //校验jwtToken的合法性
        if (StringUtils.isNotEmpty(jwtToken) && JwtUtil.verify(jwtToken, JwtUtil.getUserId(jwtToken), jwtSecret)) {
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        try {
                            exchange.getResponse().getHeaders().add(HeaderEnum.TOKEN.getName(), JwtUtil.refresh(jwtToken, jwtSecret));
                            exchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", HeaderEnum.TOKEN.getName());
                        } catch (UnsupportedOperationException e) {
                            //ReadOnlyHttpHeaders不支持设置
                        }
                    })
            );
        }

        //不合法(响应未登录的异常)
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置body
        ErrorResponse resp           = new ErrorResponse(401, "未登录");
        DataBuffer    bodyDataBuffer = response.bufferFactory().wrap(JSON.toJSONString(resp).getBytes());

        return response.writeWith(Mono.just(bodyDataBuffer));
    }


}