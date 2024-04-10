package ba.unsa.etf.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RecordsHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecordsHandlerApplication.class, args);
    }

}
