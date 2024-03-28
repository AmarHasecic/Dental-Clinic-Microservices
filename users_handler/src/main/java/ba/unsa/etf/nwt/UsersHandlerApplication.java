package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.UserEntity;
import ba.unsa.etf.nwt.repository.UsersRepository;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersHandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsersHandlerApplication.class, args);
	}


}
