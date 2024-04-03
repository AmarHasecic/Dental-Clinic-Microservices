package ba.unsa.etf.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppointmentHandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppointmentHandlerApplication.class, args);
	}

}
