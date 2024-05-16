package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
}
