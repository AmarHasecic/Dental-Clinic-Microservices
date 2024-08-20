package ba.unsa.etf.nwt.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class RequestLoggingFilter implements GlobalFilter {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

            String path = exchange.getRequest().getURI().getPath();

            if (path.equals("/auth/health")) {
                System.out.println("Health check attempt detected: " + path);
            }

            return chain.filter(exchange);
        }

}
