package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.DentistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistsRepository extends CrudRepository<DentistEntity, Long> {
}
