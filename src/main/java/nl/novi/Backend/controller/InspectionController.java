package nl.novi.Backend.controller;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class InspectionController {

    private final InspectionService inspectionService;
    @Autowired
    public InspectionController(InspectionService inspectionService){

        this.inspectionService=inspectionService;
    }
    @GetMapping("/inspections")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> getAllInspection(){
        return inspectionService.getAllInspection();
    }

    @GetMapping("/inspections/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public Inspection getInspectionById(@PathVariable ("inspectionNumber") Long inspectionNumber){
        return inspectionService.getInspectionById(inspectionNumber);
    }


    @PostMapping("/inspections/{numberPlate}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addNewInspectionToCar(@PathVariable ("numberPlate") String numberPlate,
                                                   @RequestBody Inspection inspection)  {

        return inspectionService.addNewInspectionToCar(numberPlate, inspection);

    }
    /*@PostMapping("/inspections/inventories")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> addInspectionWithItems(@RequestBody Inspection inspection) {
        return inspectionService.addInspectionWithItems(inspection);
    }*/
    @PutMapping("/inspections/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> updateInspectionById(@PathVariable("inspectionNumber") Long inspectionNumber,
                                           @RequestBody Inspection aNewInspection){
        return inspectionService.updateInspectionById(inspectionNumber, aNewInspection);
    }
    @DeleteMapping("/inspections/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> deleteInspectionById(@PathVariable("inspectionNumber") Long inspectionNumber){
        return inspectionService.deleteInspectionById(inspectionNumber);
    }

    @GetMapping("/inspections/checkstatus/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','USER_TEC','ADMIN')")
    public ResponseEntity<?> checkInspectionRepairStatus(@PathVariable ("inspectionNumber") Long inspectionNumber){
        return inspectionService.checkInspectionRepairStatus(inspectionNumber);

    }





}
