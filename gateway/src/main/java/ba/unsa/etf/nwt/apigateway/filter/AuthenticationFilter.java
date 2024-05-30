package ba.unsa.etf.nwt.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate template;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                //TODO: Make this GET quest work...

                String url = UriComponentsBuilder.fromHttpUrl("http://USERS-API/auth/validate")
                        .queryParam("token", authHeader)
                        .toUriString();
                ResponseEntity<String> response = template.getForEntity(url, String.class);

                // Reading the response status
                int statusCode = response.getStatusCodeValue();
                String responseBody = response.getBody();

                if (statusCode != 200) {
                    throw new RuntimeException("Failed to validate token: " + responseBody);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}