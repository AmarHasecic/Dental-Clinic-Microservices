package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}
