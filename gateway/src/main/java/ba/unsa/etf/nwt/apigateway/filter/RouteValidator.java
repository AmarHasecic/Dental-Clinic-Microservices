package ba.unsa.etf.nwt.apigateway.filter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/**",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> matchUri(uri, request.getURI().getPath()));

    private boolean matchUri(String pattern, String path) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, path);
    }

}