package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    public CarService(CarRepository carRepository, CustomerService customerService) {
        this.carRepository = carRepository;
        this.customerService = customerService;
    }

    public ResponseEntity<?> getAllCar() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return ResponseEntity.ok().body(cars);
    }

    public ResponseEntity<?> addCarToCustomer(Long customerId, Car car) {
        Customer newCustomer = customerService.getCustomerById(customerId);
        if (!(newCustomer == null)) {
            car.setCustomer(newCustomer);
            carRepository.save(car);
            return ResponseEntity.ok().body(new MessageResponse("This car is now added."));
        }
        return ResponseEntity.badRequest().body("The customer does not exist.");


    }

    public Car getCarById(String numberPlate) {
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if (possibleCar.isPresent()) {
            return possibleCar.get();
        }
        return null;
    }

    public ResponseEntity<?> updateCarById(String numberPlate, Car aNewCar) {
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if (possibleCar.isPresent()) {
            possibleCar.get().setInspections(aNewCar.getInspections());
            possibleCar.get().setModel(aNewCar.getModel());
            possibleCar.get().setMake(aNewCar.getMake());
            possibleCar.get().setNumberPlate(aNewCar.getNumberPlate());
            carRepository.save(possibleCar.get());
            return ResponseEntity.ok().body("The car is successfully updated.");
        }
        return ResponseEntity.badRequest().body("Error, please check again.");
    }

    public ResponseEntity<?> deleteCarById(String numberPlate) {
        Optional<Car> possibleCar = carRepository.findById(numberPlate);
        if (possibleCar.isPresent()) {
            carRepository.deleteById(numberPlate);
            return ResponseEntity.ok().body("The car is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Error, please check the number plate again.");
    }
}


