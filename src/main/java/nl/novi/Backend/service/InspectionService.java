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
import java.util.Optional;


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
         return inspectionRepository.findAll();

    }

    public Inspection getInspectionById(Long inspectionNumber){
        Optional <Inspection> possibleInspeciton = inspectionRepository.findByInspectionNumber(inspectionNumber);
                if(possibleInspeciton.isPresent()){
                    return possibleInspeciton.get();
                }
                return null;

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

    public Inspection updateInspectionById(Long inspectionNumber, Inspection aNewInspection){
        Inspection possibleInspection = getInspectionById(inspectionNumber);

            possibleInspection.setInventoryList(aNewInspection.getInventoryList());
            possibleInspection.setAgreeToRepair(aNewInspection.getAgreeToRepair());
            possibleInspection.setInspectionComplete(aNewInspection.getInspectionComplete());
            possibleInspection.setInspectionDate(aNewInspection.getInspectionDate());
            possibleInspection.setInspectionFee(aNewInspection.getInspectionFee());
            possibleInspection.setInspectionResult(aNewInspection.getInspectionResult());
            possibleInspection.setQuantities(aNewInspection.getQuantities());
            possibleInspection.setRepairComplete(aNewInspection.getRepairComplete());
            possibleInspection.setInventoryList(aNewInspection.getInventoryList());
            possibleInspection.setInvoice(aNewInspection.getInvoice());
            possibleInspection.setRepairDate(aNewInspection.getRepairDate());
            inspectionRepository.save(possibleInspection);
        return possibleInspection;
    }

    public ResponseEntity<?> deleteInspectionById(Long inspectionNumber){
       Optional <Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
       if(possibleInspection.isPresent()){
           inspectionRepository.deleteById(inspectionNumber);
           return ResponseEntity.ok().body("The inspection is deleted sucessfully.");
       }
       return  ResponseEntity.badRequest().body("Error, please check the inspection number again.");


    }




}
