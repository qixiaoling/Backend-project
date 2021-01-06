package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InspectionService {

    private InspectionRepository inspectionRepository;
    private CarRepository carRepository;
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    public void setInspectionRepository(InspectionRepository inspectionRepository){
        this.inspectionRepository=inspectionRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }



    public List<Inspection> getAllInspection(){
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.findAll().forEach(inspections::add);
        return inspections;
    }

    public List<Inspection> addInspection(Inspection inspection){
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.save(inspection);
        return inspections;
    }

    public ResponseEntity<?> addInspectionWithItems(Inspection inspection){
        Inspection newInspection = new Inspection();
        newInspection.setInspectionNumber(inspection.getInspectionNumber());
        newInspection.getInventoryList().addAll(
                inspection.getInventoryList().stream().map(v->{
                    Inventory vv=inventoryService.findInventoryById(v.getItemId());
                    vv.getInspectionList().add(newInspection);
                    return vv;
                }).collect(Collectors.toList()));
        inspectionRepository.save(newInspection);
        return ResponseEntity.ok().body(new MessageResponse("Items are added into this Inspection."));
    }
    /*public ResponseEntity<?> addItemsToInspection(Long inspectionNumber, List<Inventory> inventoryList) {
        Inspection inspectionFromDB = inspectionRepository.findByInspectionNumber(inspectionNumber);
        inspectionFromDB.setInventoryList(inventoryList);
        inspectionRepository.save(inspectionFromDB);
    return ResponseEntity.ok().body(new MessageResponse("New items are added onto this inspection"));
    }*/

    public ResponseEntity<?> addNewInspectionToCar(String numberPlate, Inspection inspection) {

        Optional<Car> carFromDb = carRepository.findByNumberPlate(numberPlate);
        if(carFromDb.isPresent()) {
            inspection.setCar(carFromDb.get());
            inspectionRepository.save(inspection);
            return ResponseEntity.ok().body(new MessageResponse("Inspection Report added."));
        }
        return ResponseEntity.badRequest()
                .body("Error: car does not exist.");
    }




}
