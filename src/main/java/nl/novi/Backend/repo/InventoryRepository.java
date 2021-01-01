package nl.novi.Backend.repo;

import nl.novi.Backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    Inventory findByItem(Long item);
}
