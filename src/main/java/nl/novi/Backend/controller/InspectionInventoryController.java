package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.InspectionInventory;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.service.InspectionInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class InspectionInventoryController {

    @Autowired
    private InspectionInventoryService inspectionInventoryService;

    public InspectionInventoryController(InspectionInventoryService inspectionInventoryService) {
        this.inspectionInventoryService = inspectionInventoryService;
    }


    @PostMapping("/addinginventories/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addingInventory(@PathVariable("inspectionNumber") Long inspectionNumber,
                                             @RequestBody Inventory inventory){
        return inspectionInventoryService.addingInventory(inspectionNumber, inventory);
    }


}
