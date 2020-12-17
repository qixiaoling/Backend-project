package nl.novi.Backend.repo;

import nl.novi.Backend.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    Inspection findByInspectionNumber(Long inspectionNumber);
}
