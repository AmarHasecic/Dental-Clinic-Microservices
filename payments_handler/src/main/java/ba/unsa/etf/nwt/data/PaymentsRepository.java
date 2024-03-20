package ba.unsa.etf.nwt.data;

import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<PaymentEntity, Long> {
}
