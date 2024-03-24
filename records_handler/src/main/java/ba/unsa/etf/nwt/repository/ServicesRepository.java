package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends CrudRepository<ServiceEntity, Long> {
}
