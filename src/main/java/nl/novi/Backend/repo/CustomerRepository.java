package nl.novi.Backend.repo;

import nl.novi.Backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     Optional<Customer> findById(Long customerId);
     Boolean existsByEmail(String email);

}
