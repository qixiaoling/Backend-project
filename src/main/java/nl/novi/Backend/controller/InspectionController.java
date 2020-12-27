package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class InspectionController {

    private InspectionService inspectionService;
    @Autowired
    public InspectionController(InspectionService inspectionService){
        this.inspectionService=inspectionService;
    }
    @GetMapping("/inspections")
    public List<Inspection> getAllInspection(){
        return inspectionService.getAllInspection();
        }
    @PostMapping("/inspections")
    public List<Inspection> addInspection(@RequestBody Inspection inspection){
        return inspectionService.addInspection(inspection);
    }

    @PostMapping("/inspections/{numberPlate}")
    public ResponseEntity<?> addNewInspectionToCar(@PathVariable String numberPlate,
                                                   @RequestBody Inspection inspection)  {

        return inspectionService.addNewInspectionToCar(numberPlate, inspection);

    }
    @PostMapping("/inspections/inventories")

    public ResponseEntity<?> addInspectionWithItems(@RequestBody Inspection inspection) {
        return inspectionService.addInspectionWithItems(inspection);
    }

    /*@PostMapping("/inspections/{inspectionNumber}")
    public ResponseEntity<?> addItemsToInspection(@PathVariable ("inspectionNumber") Long inspectionNumber,
                                                  @RequestBody List<Inventory> inventoryList){
        return inspectionService.addItemsToInspection(inspectionNumber, inventoryList);
    }*/





}
