package nl.novi.Backend.repo;

import nl.novi.Backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    Optional<Inventory> findByItemId(Long itemId);
}
