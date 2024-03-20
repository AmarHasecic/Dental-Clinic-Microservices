package ba.unsa.etf.nwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AppointmentHandlerApplication implements CommandLineRunner {
	@Autowired
	public RecordsRepository recordsRepository;
	@Autowired
	private
	public static void main(String[] args) {
		SpringApplication.run(AppointmentHandlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RecordEntity record = new RecordEntity();
		record.setId(5);
		record.setPatientID(2);
		record.setImage("3");
		record.setAppointmentID(7);

		recordsRepository.save(record);
	}
}
