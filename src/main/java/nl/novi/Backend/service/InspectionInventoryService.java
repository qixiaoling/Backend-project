package nl.novi.Backend.service;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.InspectionInventory;
import nl.novi.Backend.model.InspectionInventoryId;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.InspectionInventoryRepository;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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

    public InspectionInventory addQuantity(Long inspectionNumber, Long itemId, InspectionInventory inspectionInventory){
        Optional <InspectionInventory> possibleInspectionInventory =
                inspectionInventoryRepository.findById(new InspectionInventoryId(inspectionNumber, itemId));
        if(possibleInspectionInventory.isPresent()){
            possibleInspectionInventory.get().setInventoryQuantities(inspectionInventory.getInventoryQuantities());
            InspectionInventory theOneToAdd = new InspectionInventory();
            theOneToAdd = possibleInspectionInventory.get();
            inspectionInventoryRepository.save(theOneToAdd);
            return theOneToAdd;
        }
        return null;
    }

    public ResponseEntity<?> removeInventory(Long inspectionNumber, Inventory inventory){
        Optional <Inspection> inspection = inspectionRepository.findById(inspectionNumber);
        if (inspection.isPresent()){
            for (Iterator <InspectionInventory> iterator = inspection.get().getInventoryNewList().iterator();
                 iterator.hasNext();){
                InspectionInventory inspectionInventory = iterator.next();

                if(inspectionInventory.getInspection().equals(inspection.get()) &&
                            inspectionInventory.getInventory().equals(inventory)){
                     iterator.remove();
                     inspectionInventory.getInventory().getInspectionNewList().remove(inspectionInventory);
                     inspectionInventory.setInspection(null);
                     inspectionInventory.setInspection(null);
                     return ResponseEntity.ok().body(new MessageResponse("This inventory is removed successfully."));
                    }
                }
            }
        return ResponseEntity.badRequest().body("Error, please check the inspection number again.");
        }



}
