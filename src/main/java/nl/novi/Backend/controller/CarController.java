package nl.novi.Backend.controller;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class CarController {

    private final CarService carService;
    @Autowired
    public CarController(CarService carService){
        this.carService=carService;
    }

    @GetMapping("/cars")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'USER_BAC', 'USER_TRE', 'USER_TEC', 'ADMIN')")
    public ResponseEntity<?> getAllCar(){
        return carService.getAllCar();
    }

    @PostMapping("/cars/{customerid}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public ResponseEntity<?> addCarToCustomer(@PathVariable ("customerid") Long customerId,
                                                 @RequestBody Car car){
        return carService.addCarToCustomer(customerId,car);
    }
    @GetMapping("/cars/{numberPlate}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'USER_BAC', 'USER_TRE', 'USER_TEC', 'ADMIN')")
    public Car getCarById(@PathVariable("numberPlate") String numberPlate){
        return carService.getCarById(numberPlate);
    }
    @PutMapping("/cars/{numberPlate}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public ResponseEntity<?> updateCarById(@PathVariable("numberPlate") String numberPlate, @RequestBody Car aNewCar){
        return carService.updateCarById(numberPlate, aNewCar);
    }
    @DeleteMapping("/cars/{numberPlate}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public ResponseEntity<?> deleteCarFromCustomer(@PathVariable("numberPlate") String numberPlate){
        return carService.deleteCarById(numberPlate);
    }



}
