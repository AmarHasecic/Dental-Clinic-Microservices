package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends CrudRepository<PatientEntity,Long> {
}
