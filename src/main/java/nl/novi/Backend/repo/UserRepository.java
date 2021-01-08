package nl.novi.Backend.repo;

import nl.novi.Backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUserName (String userName);
    Boolean existsByUserName(String userName);

}
