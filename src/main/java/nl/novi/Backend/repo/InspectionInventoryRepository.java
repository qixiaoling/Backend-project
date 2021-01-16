package nl.novi.Backend.repo;

import nl.novi.Backend.model.InspectionInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionInventoryRepository extends JpaRepository<InspectionInventory, Long> {
}
