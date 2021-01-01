package nl.novi.Backend.service;


import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Autowired

    public List<Inventory> getAllInventories(){
        List<Inventory> inventories = new ArrayList<>();
        inventoryRepository.findAll().forEach(inventories::add);
        return inventories;
    }
    public List<Inventory> addInventories(Inventory inventory){
        List<Inventory> inventories = new ArrayList<>();
        inventoryRepository.save(inventory);
        return inventories;

    }

    public Inventory findInventoryById(Long itemId){
        return inventoryRepository.findByItemId(itemId);
    }

}
