package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.RecordsRepository;
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
		record.setPatient(null);
		record.setImage("3");
		record.setAppointment(null);

		recordsRepository.save(record);
	}
}
