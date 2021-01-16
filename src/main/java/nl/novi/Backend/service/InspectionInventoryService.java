package nl.novi.Backend.service;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.InspectionInventory;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.InspectionInventoryRepository;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionInventoryService {

    @Autowired
    private InspectionInventoryRepository inspectionInventoryRepository;
    @Autowired
    private InspectionRepository inspectionRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public InspectionInventoryService() {
    }

    public InspectionInventoryService(InspectionInventoryRepository inspectionInventoryRepository, InspectionRepository inspectionRepository,
                                      InventoryRepository inventoryRepository) {
        this.inspectionInventoryRepository = inspectionInventoryRepository;
        this.inspectionRepository = inspectionRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public InspectionInventoryService(InspectionInventoryRepository inspectionInventoryRepository) {
        this.inspectionInventoryRepository = inspectionInventoryRepository;
    }
    public InspectionInventory addInspectionInventoryToInspection(Long inspectionNumber, InspectionInventory inspectionInventory) {
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()) {
            inspectionInventory.setInspection(possibleInspection.get());
            inspectionInventoryRepository.save(inspectionInventory);
            return inspectionInventory;
        }
        return null;
    }

    public InspectionInventory addInspectionInventoryToInventory(Long itemId, InspectionInventory inspectionInventory) {
        Optional<Inventory> possibleInventory = inventoryRepository.findById(itemId);
        if (possibleInventory.isPresent()) {
            inspectionInventory.setInventory(possibleInventory.get());
            inspectionInventoryRepository.save(inspectionInventory);
            return inspectionInventory;
        }
        return null;
    }

    public ResponseEntity<?> addInventoryToInspection(Long inspectionNumber, Long itemId, InspectionInventory baseInspectionInventory){
        InspectionInventory firstOne = addInspectionInventoryToInspection(inspectionNumber,baseInspectionInventory);
        InspectionInventory secondOne = addInspectionInventoryToInventory(itemId, firstOne);
        inspectionInventoryRepository.save(secondOne);
        return ResponseEntity.ok().body(new MessageResponse("Inventory is now added to the inspection."));
    }

    public ResponseEntity<?> addInventory(Inspection inspection, Inventory inventory){
        InspectionInventory inspectionInventory = new InspectionInventory(inspection, inventory);
        inspection.getInventoryNewList().add(inspectionInventory);
        inventory.getInspectionNewList().add(inspectionInventory);
        return ResponseEntity.ok().body("This inventory is now added into this inspection.");
    }
    public ResponseEntity<?> addingInventory(Long inspectionNumber, Inventory inventory){
        Optional <Inspection> inspection = inspectionRepository.findById(inspectionNumber);
        if(inspection.isPresent()){
        InspectionInventory inspectionInventory = new InspectionInventory(inspection.get(), inventory);
        inspection.get().getInventoryNewList().add(inspectionInventory);
        inventory.getInspectionNewList().add(inspectionInventory);
        inspectionRepository.save(inspection.get());
        inventoryRepository.save(inventory);
        return ResponseEntity.ok().body("This inventory is now added into this inspection.");
    }
        return ResponseEntity.badRequest().body("Error, please check inspection number again.");
    }


}
