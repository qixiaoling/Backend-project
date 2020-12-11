package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
@Service
public class InspectionService {

    private InspectionRepository inspectionRepository;
    private CarRepository carRepository;

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
