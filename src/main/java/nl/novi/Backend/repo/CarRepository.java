package nl.novi.Backend.repo;

import nl.novi.Backend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    //Boolean existsByNumberPlate(String numberPlate);
    Optional<Car> findByNumberPlate(String numberPlate);


}
