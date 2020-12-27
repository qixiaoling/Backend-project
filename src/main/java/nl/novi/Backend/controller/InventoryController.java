package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class InventoryController {
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService=inventoryService;
    }
    @GetMapping("/inventories")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER_BAC', 'ROLE_USER_FRO','ROLE_TRE', 'ROLE_TEC')")
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @PostMapping("/inventories")
    @PreAuthorize("hasAuthority('inventory:write')")
    public List<Inventory> addInventories(@RequestBody Inventory inventory){
        return inventoryService.addInventories(inventory);
    }

}
