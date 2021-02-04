package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.repo.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Mock
    List<Car> cars = new ArrayList<>();

    @BeforeEach
    public void setup() {
        cars = new ArrayList<>();
        cars.add(new Car("89-JKP-38", "Benz", "Cabrio"));
        cars.add(new Car("89-JKP-30", "Benz", "Cabrio"));
    }

    @Test
    public void getAllCarTest(){

        Mockito.when(carRepository.findAll()).thenReturn(cars);
        assertThat(carService.getAllCar().size()).isEqualTo(2);


    }



}
