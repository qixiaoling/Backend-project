package nl.novi.Backend.service;

import nl.novi.Backend.model.Customer;
import nl.novi.Backend.model.Gender;
import nl.novi.Backend.repo.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CustomerTest {
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    Customer customer;



    @BeforeEach
    public void setup() {

        Customer customer = new Customer();
        customer.setCustomerId((long) 1);
        customer.setFirstName("Daisy");
        customer.setLastName("Duck");
        customer.setEmail("daisyduck@gmail.com");
        customer.setGender(Gender.FEMALE);
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void getCustomerByIdTest(){
        Mockito.when(customerRepository.findById((long) 1))
                .thenReturn(Optional.of(customer));

        customerService.getCustomerById((long) 1);

        assertThat(customerService.getCustomerById((long)1).equals(customer));


    }

}
