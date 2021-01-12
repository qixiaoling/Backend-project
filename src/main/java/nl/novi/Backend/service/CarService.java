package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;


    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    public List<Car> getAllCar(){
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    public List<Car> addCars(Car car){
        List<Car> cars = new ArrayList<>();
        carRepository.save(car);
        return cars;
    }
    public Car getCarById(String numberPlate ){
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if(possibleCar.isPresent()){
            return possibleCar.get();
        }
        return null;
    }

    public Car updateCarById(String numberPlate, Car car){
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if(possibleCar.isPresent()){
            Car aCar = possibleCar.get();
            return aCar;
        }
        return null;
    }

    public ResponseEntity<?> deleteCustomerById(String numberPlate){
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if(possibleCar.isPresent()){
            carRepository.deleteById(numberPlate);
            return ResponseEntity.ok().body("The car is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the number plate again.");
    }

}
