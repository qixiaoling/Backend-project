package nl.novi.Backend.service;

import net.bytebuddy.asm.Advice;
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

}
