package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<ServiceEntity, Long> {
}
