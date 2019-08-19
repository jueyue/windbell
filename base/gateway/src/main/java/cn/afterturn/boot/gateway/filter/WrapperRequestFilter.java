package cn.afterturn.boot.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author by jueyue on 19-8-15.
 */
public class WrapperRequestFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        addRealIP(exchange);
        return chain.filter(exchange);
    }

    private void addRealIP(ServerWebExchange exchange) {
    }
}
