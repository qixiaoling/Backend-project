package nl.novi.Backend.service;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.repo.CarRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CarServiceTest {
    @Autowired
    private CarService carService;
    @MockBean
    private CarRepository carRepository;
    @Test
    public void getAllCarTest(){
        when(carRepository.findAll()).thenReturn(Stream
                .of(new Car("89-JKP-38", "Benz", "Cabrio"),
                        new Car("63-JKX-17", "Audi", "A6")).collect(Collectors.toList()));
        assertThat(carService.getAllCar().size()).isEqualTo(2);

        Optional<Car> carOptional = Optional.of(new Car("89-JKP-38", "Benz", "Cabrio"));
        assertEquals(Optional.of(new Car("89-JKP-38", "Benz", "Cabrio")), carOptional);

    }



}
