package nl.novi.Backend.controller;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CarController {

    private CarService carService;
    @Autowired
    public CarController(CarService carService){
        this.carService=carService;
    }
    @GetMapping("/cars")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public List<Car> getAllCar(){
        return carService.getAllCar();
    }

    @PostMapping("/cars")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public List<Car> addCar(@RequestBody Car car){
        return carService.addCars(car);
    }

}
