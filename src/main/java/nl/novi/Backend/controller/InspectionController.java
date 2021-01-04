package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public List<Inspection> getAllInspection(){
        return inspectionService.getAllInspection();
        }
    @PostMapping("/inspections")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public List<Inspection> addInspection(@RequestBody Inspection inspection){
        return inspectionService.addInspection(inspection);
    }

    @PostMapping("/inspections/{numberPlate}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addNewInspectionToCar(@PathVariable String numberPlate,
                                                   @RequestBody Inspection inspection)  {

        return inspectionService.addNewInspectionToCar(numberPlate, inspection);

    }
    @PostMapping("/inspections/inventories")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addInspectionWithItems(@RequestBody Inspection inspection) {
        return inspectionService.addInspectionWithItems(inspection);
    }




}
