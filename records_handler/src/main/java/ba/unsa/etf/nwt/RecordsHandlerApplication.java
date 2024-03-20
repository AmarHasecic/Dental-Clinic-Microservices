package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.data.RecordEntity;
import ba.unsa.etf.nwt.data.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecordsHandlerApplication implements CommandLineRunner {
	@Autowired
	public RecordsRepository recordsRepository;
	public static void main(String[] args) {
		SpringApplication.run(RecordsHandlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RecordEntity record = new RecordEntity();
		record.setId(1);
		record.setPatientID(2);
		record.setImage("3");
		record.setAppointmentID(3);

		recordsRepository.save(record);
	}
}
