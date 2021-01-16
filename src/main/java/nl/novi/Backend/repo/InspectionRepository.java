package nl.novi.Backend.repo;

import nl.novi.Backend.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    Optional<Inspection> findByInspectionNumber(Long inspectionNumber);
}
