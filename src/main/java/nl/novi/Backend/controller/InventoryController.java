package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }


    @PostMapping("/inventories/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public Inventory findInventoryById(Long itemId){

        return inventoryService.findInventoryById(itemId);
    }

    @PutMapping("/inventories/{itemId}")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public ResponseEntity<?> updateInventoryById(@PathVariable("itemId") Long itemId, @RequestBody Inventory inventory){
        return inventoryService.updateInventory(itemId, inventory);
    }

}
