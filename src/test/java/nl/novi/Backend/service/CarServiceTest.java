package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.repo.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarRepository carRepository;

    private List<Car> cars = new ArrayList<>();
    //Arrange
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cars = new ArrayList<>();
        cars.add(new Car("89-JKP-38", "Benz", "Cabrio"));
        cars.add(new Car("89-JKP-30", "Benz", "Cabrio"));
    }

    @Test
    public void getAllCarTest(){
        //Arrange
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        //Act & Assert
        assertThat(carService.getAllCar().size()).isEqualTo(2);

    }



}
