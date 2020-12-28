package nl.novi.Backend.security_repo;

import nl.novi.Backend.security_model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository {

    Optional<User> findByUserName (String userName);

}
