package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AppointmentHandlerApplication {
	@Autowired
	public AppointmentsRepository appointmentsRepository;
	public static void main(String[] args) {
		SpringApplication.run(AppointmentHandlerApplication.class, args);
	}
}
