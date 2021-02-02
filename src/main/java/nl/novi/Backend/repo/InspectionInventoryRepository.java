package nl.novi.Backend.repo;

import nl.novi.Backend.model.InspectionInventory;
import nl.novi.Backend.model.InspectionInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionInventoryRepository extends JpaRepository<InspectionInventory, InspectionInventoryId> {

    //pseudo code
    //deleteByNAAMEERSTEIDAndNaamTweedeId(long id 1, long id 2)
    //boolean deleteByIdInspectionIdAndIdInventoryId(Long inspectionId, Long inventoryId);


    @Override
    void deleteById(InspectionInventoryId inspectionInventoryId);
}
