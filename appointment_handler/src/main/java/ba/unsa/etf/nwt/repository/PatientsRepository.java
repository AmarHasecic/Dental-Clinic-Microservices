package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.PatientEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientsRepository extends CrudRepository<PatientEntity,Long> {
}
