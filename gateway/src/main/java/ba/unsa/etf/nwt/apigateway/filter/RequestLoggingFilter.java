package ba.unsa.etf.nwt.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Component
@Order(1)
public class RequestLoggingFilter implements GlobalFilter {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

            String path = exchange.getRequest().getURI().getPath();
            if (path.equals("/auth/health")) {
                String msg = LocalDateTime.now() + ": Health check attempt detected!";
                kafkaTemplate.send("logs", msg);
            }

            return chain.filter(exchange);
        }

}
