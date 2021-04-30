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

    public ResponseEntity<?> addingInventory(Long inspectionNumber, Inventory inventory) {
        Optional<Inspection> inspection = inspectionRepository.findById(inspectionNumber);
        if (inspection.isPresent()) {
            Inspection inspectionFound = inspection.get();
            InspectionInventory inspectionInventory = new InspectionInventory(inspectionFound, inventory);
            inspectionFound.getInventoryNewList().add(inspectionInventory);
            inventory.getInspectionNewList().add(inspectionInventory);
            inspectionRepository.save(inspectionFound);
            inventoryRepository.save(inventory);
            inspectionInventoryRepository.save(inspectionInventory);
            return ResponseEntity.ok().body("This inventory is now added into this inspection.");
        } else {
            return ResponseEntity.badRequest().body("Error, please check inspection number again.");
        }
    }

    public ResponseEntity<?> addQuantity(Long inspectionNumber, Long itemId, InspectionInventory inspectionInventory){
        Optional <InspectionInventory> possibleInspectionInventory =
                inspectionInventoryRepository.findById(new InspectionInventoryId(inspectionNumber, itemId));
        if(possibleInspectionInventory.isPresent()){
            possibleInspectionInventory.get().setInventoryQuantities(inspectionInventory.getInventoryQuantities());

            inspectionInventoryRepository.save(possibleInspectionInventory.get());
            return ResponseEntity.ok().body(new MessageResponse("Quantitiy has been added."));
        }
        return ResponseEntity.badRequest().body("Please check the inspection number again.");
    }

    public void removeInventory(InspectionInventoryId id){
        inspectionInventoryRepository.deleteById(id);



    }

    /*public ResponseEntity<?> removeInventory(Long inspectionNumber, Inventory inventory){
        Optional <Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()){
            for (Iterator <InspectionInventory> iterator = possibleInspection.get().getInventoryNewList().iterator();
                 iterator.hasNext();){
                InspectionInventory inspectionInventory = iterator.next();

                if(inspectionInventory.getInspection().equals(possibleInspection.get()) &&
                            inspectionInventory.getInventory().equals(inventory)){
                     iterator.remove();
                     inspectionInventory.getInventory().getInspectionNewList().remove(inspectionInventory);
                     inspectionInventory.setInspection(null);
                     inspectionInventory.setInventory(null);
                     inspectionInventoryRepository.save(inspectionInventory);
                     return ResponseEntity.ok().body(new MessageResponse("This inventory is removed successfully."));
                    }
                }
            }
        return ResponseEntity.badRequest().body("Error, please check the inspection number again.");
        }*/





}
