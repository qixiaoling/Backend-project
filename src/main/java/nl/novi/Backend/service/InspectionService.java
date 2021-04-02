package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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


    public ResponseEntity<?> getAllInspection() {
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.findAll().forEach(inspections::add);
        return ResponseEntity.ok().body(inspections);

    }

    public Inspection getInspectionById(Long inspectionNumber) {
        Optional<Inspection> possibleInspeciton = inspectionRepository.findByInspectionNumber(inspectionNumber);
        if (possibleInspeciton.isPresent()) {
            return possibleInspeciton.get();
        }
        return null;
    }


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
            return ResponseEntity.ok().body("The inspection is updated successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the inspection id again.");
    }

    public ResponseEntity<?> deleteInspectionById(Long inspectionNumber) {
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()) {
            inspectionRepository.deleteById(inspectionNumber);
            return ResponseEntity.ok().body("The inspection is deleted sucessfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check the inspection number again.");


    }

    public ResponseEntity<?> checkInspectionRepairStatus(Long inspectionNumber){
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if(possibleInspection.isPresent()){
            if (possibleInspection.get().getInspectionComplete()==true){
                if(possibleInspection.get().getAgreeToRepair().equals(Boolean.FALSE)){
                    return ResponseEntity.ok().body("Inspection is done, no repair needed, " +
                            "please inform customer to collect the vehicle");
                }else {
                    if (possibleInspection.get().getRepairComplete() == true) {
                        return ResponseEntity.ok().body("Inspection and repair are done, please inform" +
                                "customer to collect the vehicle.");
                    }else{
                        return ResponseEntity.ok().body("Inspection is done, repair is in process.");
                    }
                }
            }else ResponseEntity.ok().body("Inspection is in process.");
        }
        return ResponseEntity.badRequest().body("Error, cannot find the inspection, please check the inspection number.");

    }
}





