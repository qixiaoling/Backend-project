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
    @PostMapping("/inventories")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public List <Inventory> addInventory(@RequestBody Inventory inventory){
        return inventoryService.addInventory(inventory);
    }

    @GetMapping("/inventories")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }


    @GetMapping("/inventories/{itemId}")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public Inventory findInventoryById(@PathVariable("itemId") Long itemId){

        return inventoryService.findInventoryById(itemId);
    }

    @PutMapping("/inventories/{itemId}")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public ResponseEntity<?> updateInventoryById(@PathVariable("itemId") Long itemId, @RequestBody Inventory inventory){
        return inventoryService.updateInventoryById(itemId, inventory);
    }
    @DeleteMapping("/inventories/{itemId}")
    @PreAuthorize("hasAnyAuthority('USER_BAC','ADMIN')")
    public ResponseEntity<?> deleteInventoryById(@PathVariable("itemId") Long itemId){
        return inventoryService.deleteInventoryById(itemId);
    }
}
