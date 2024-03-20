package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.data.UserEntity;
import ba.unsa.etf.nwt.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersHandlerApplication implements CommandLineRunner {

	@Autowired
	private UsersRepository usersRepository;
	public static void main(String[] args) {
		SpringApplication.run(UsersHandlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(2);
		userEntity.setAccessLevel('A');
		userEntity.setDentistID(3);
		userEntity.setMail("mail2@mail.com");
		userEntity.setFirstName("Ime");
		userEntity.setLastName("Prezime");
		userEntity.setPassword("pass");
		userEntity.setPatientID(1);

		usersRepository.save(userEntity);
	}

}
