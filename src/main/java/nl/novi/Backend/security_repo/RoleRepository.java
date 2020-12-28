package nl.novi.Backend.security_repo;

import nl.novi.Backend.security_model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository {

    Optional<Role> findByRoleName(String roleName);
}
