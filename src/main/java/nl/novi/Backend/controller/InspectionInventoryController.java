package nl.novi.Backend.controller;

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
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.util.List;
@RestController("/")
public class InspectionInventoryController {

    @Autowired
    private InspectionInventoryService inspectionInventoryService;

    public InspectionInventoryController(InspectionInventoryService inspectionInventoryService) {
        this.inspectionInventoryService = inspectionInventoryService;
    }

    @PostMapping("/inspectionsinventories/addtoinspections/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public InspectionInventory addInspectionInventoryToInspection(@PathVariable("inspectionNumber") Long inspectionNumber,
                                                      @RequestBody InspectionInventory inspectionInventory) {
        return inspectionInventoryService.addInspectionInventoryToInspection(inspectionNumber, inspectionInventory );
    }
    @PostMapping("/inspectionsinventories/addtoinventories/{itemid}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public InspectionInventory addInspectionInventoryToInventory(@PathVariable("itemid") Long itemId,
                                                                  @RequestBody InspectionInventory inspectionInventory) {
        return inspectionInventoryService.addInspectionInventoryToInventory(itemId, inspectionInventory );
    }
    @PostMapping("/inspectionsinventories/{inspectionNumber}/{itemId}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addInventoryToInspection(@PathVariable("inspectionNumber") Long inspectionNumber,
                                                                  @PathVariable("itemId") Long itemId,
                                                                  @RequestBody InspectionInventory inspectionInventory) {
        return inspectionInventoryService.addInventoryToInspection(inspectionNumber, itemId, inspectionInventory );
    }


}
