package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Inventory;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;


import java.util.List;


@Service
public class InspectionService {
    @Autowired
    private final InspectionRepository inspectionRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CarRepository carRepository;

    public InspectionService(InspectionRepository inspectionRepository, CarRepository carRepository) {
        this.inspectionRepository = inspectionRepository;
        this.carRepository = carRepository;
    }


    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public List<Inspection> getAllInspection() {
        return inspectionRepository.findAll();

    }

    public Inspection getInspectionById(Long inspectionNumber) {
        Optional<Inspection> possibleInspeciton = inspectionRepository.findByInspectionNumber(inspectionNumber);
        if (possibleInspeciton.isPresent()) {
            return possibleInspeciton.get();
        }
        return null;

    }


    //add a list to one object, in a ManyToMany relationship, both side already exist in database.
    /*public ResponseEntity<?> addInventoryToInspection(Long inspectionNumber, List<Inventory> inventoryList){
        Optional <Inspection> foundInspection = inspectionRepository.findById(inspectionNumber);
        if (foundInspection.isPresent()){
            List<Inventory> newInventoryList = new ArrayList<>();
            for(Inventory v:inventoryList){
                Inventory vv = inventoryService.findInventoryById(v.getItemId());
                newInventoryList.add(vv);
            }
            Inspection theInspection = foundInspection.get();
            theInspection.setInventoryList(newInventoryList);
            inspectionRepository.save(theInspection);
            return ResponseEntity.ok().body("Inventory is now saved to the selected inspection.");
        }
        return ResponseEntity.badRequest().body("The inspection cannot be found.");

    }*/
    //add a list to one object, in a ManyToMany relationship, the list is exists in the database, but the one object is brand new.
   /* public ResponseEntity<?> addInspectionWithItems(Inspection inspection){
        Inspection newInspection = new Inspection();
        newInspection.setInspectionNumber(inspection.getInspectionNumber());
        newInspection.getInventoryList().addAll(
                inspection.getInventoryList().stream().map(v->{
                    Inventory vv=inventoryService.findInventoryById(v.getItemId());
                    vv.getInspectionList().add(newInspection);
                    return
                    vv;
                }).collect(Collectors.toList()));
        inspectionRepository.save(newInspection);
        return ResponseEntity.ok().body(new MessageResponse("Items are added into this Inspection."));
    }*/
    //add one object to one object, in a ManyToMany relationship, both side already exist in database.
    public ResponseEntity<?> addNewInspectionToCar(String numberPlate, Inspection inspection) {

        Optional<Car> carFromDb = carRepository.findByNumberPlate(numberPlate);
        if (carFromDb.isPresent()) {
            inspection.setCar(carFromDb.get());
            inspectionRepository.save(inspection);
            return ResponseEntity.ok().body(new MessageResponse("Inspection Report added."));
        }
        return ResponseEntity.badRequest()
                .body("Error: car does not exist.");
    }

    public ResponseEntity<?> updateInspectionById(Long inspectionNumber, Inspection aNewInspection) {
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()) {
            possibleInspection.get().setAgreeToRepair(aNewInspection.getAgreeToRepair());
            possibleInspection.get().setInspectionComplete(aNewInspection.getInspectionComplete());
            possibleInspection.get().setInspectionDate(aNewInspection.getInspectionDate());
            possibleInspection.get().setInspectionFee(aNewInspection.getInspectionFee());
            possibleInspection.get().setInspectionResult(aNewInspection.getInspectionResult());
            possibleInspection.get().setRepairComplete(aNewInspection.getRepairComplete());
            possibleInspection.get().setInvoice(aNewInspection.getInvoice());
            possibleInspection.get().setRepairDate(aNewInspection.getRepairDate());
            inspectionRepository.save(possibleInspection.get());
            return ResponseEntity.ok().body("The customer is updated successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the customer id again.");
    }

    public ResponseEntity<?> deleteInspectionById(Long inspectionNumber) {
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()) {
            inspectionRepository.deleteById(inspectionNumber);
            return ResponseEntity.ok().body("The inspection is deleted sucessfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check the inspection number again.");


    }
}





