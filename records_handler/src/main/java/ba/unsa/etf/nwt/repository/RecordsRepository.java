package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.RecordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends CrudRepository<RecordEntity, Long> {
}
