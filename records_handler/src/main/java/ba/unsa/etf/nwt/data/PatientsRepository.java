package ba.unsa.etf.nwt.data;

import org.springframework.data.repository.CrudRepository;

public interface PatientsRepository extends CrudRepository<PatientEntity,Long> {
}
