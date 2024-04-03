package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentsRepository extends CrudRepository<AppointmentEntity, Long> {
}
