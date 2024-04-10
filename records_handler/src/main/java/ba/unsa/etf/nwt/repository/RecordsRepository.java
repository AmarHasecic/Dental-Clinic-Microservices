package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.RecordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordsRepository extends CrudRepository<RecordEntity, Long> {
    Optional<RecordEntity> findById(Long id);

    List<RecordEntity> findAllByPatientId(Long patinetId);
}