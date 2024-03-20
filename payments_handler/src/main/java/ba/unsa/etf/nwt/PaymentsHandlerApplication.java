package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.data.PaymentEntity;
import ba.unsa.etf.nwt.data.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PaymentsHandlerApplication implements CommandLineRunner {

	@Autowired
	private PaymentsRepository paymentsRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaymentsHandlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PaymentEntity payment = new PaymentEntity();
		payment.setPaymentDate(new Date());
		payment.setId(2);
		payment.setAppointmentID(3);
		payment.setPaymentID(14);

		paymentsRepository.save(payment);
	}
}
