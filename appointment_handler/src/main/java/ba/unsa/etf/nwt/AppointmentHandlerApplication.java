package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.data.AppointmentEntity;
import ba.unsa.etf.nwt.data.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AppointmentHandlerApplication implements CommandLineRunner {
	@Autowired
	public AppointmentsRepository appointmentsRepository;
	public static void main(String[] args) {
		SpringApplication.run(AppointmentHandlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AppointmentEntity appointmentEntity = new AppointmentEntity();
		appointmentEntity.setId(1);
		appointmentEntity.setDate(new Date());
		appointmentEntity.setPatientID(6);
		appointmentEntity.setDentistID(6);
		appointmentEntity.setNote("Test");
		appointmentEntity.setServiceID(5);

		appointmentsRepository.save(appointmentEntity);
	}
}
