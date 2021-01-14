package nl.novi.Backend.service;


import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }
    public Inventory addInventory(Inventory inventory){
        inventoryRepository.save(inventory);
        return inventory;
    }

    public Inventory findInventoryById(Long itemId) {
        Optional<Inventory> possibleInventory = inventoryRepository.findByItemId(itemId);
        if (possibleInventory.isPresent()) {
            return possibleInventory.get();
        }
        return null;
    }
    public ResponseEntity<?> updateInventoryById(Long itemId, Inventory inventory){
        Optional <Inventory> possibleInventory = inventoryRepository.findById(itemId);
        if(possibleInventory.isPresent()){
            possibleInventory.get().setItemDescription(inventory.getItemDescription());
            possibleInventory.get().setAvailableUnit(inventory.getAvailableUnit());
            possibleInventory.get().setManufactor(inventory.getManufactor());
            possibleInventory.get().setPricePerUnit(inventory.getPricePerUnit());
            inventoryRepository.save(possibleInventory.get());
            return ResponseEntity.ok().body("The inventory is successfully updated.");
        }
        return ResponseEntity.badRequest().body("Error, please check again.");
    }
    
    public ResponseEntity<?> deleteInventoryById(Long itemId){
        Optional <Inventory> possibleInventory = inventoryRepository.findById(itemId);
        if(possibleInventory.isPresent()){
            inventoryRepository.deleteById(itemId);
            return ResponseEntity.ok().body("The inventory is deleted sucessfully.");
        }
        return  ResponseEntity.badRequest().body("Error, please check the item ID again.");
    }





}