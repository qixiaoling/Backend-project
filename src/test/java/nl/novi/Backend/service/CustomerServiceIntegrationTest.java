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

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceIntegrationTest {
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    Customer customer;

    @BeforeEach
    public void setup() {

        Customer customerOne = new Customer();
        customerOne.setCustomerId((long) 1);
        customerOne.setFirstName("Daisy");
        customerOne.setLastName("Duck");
        customerOne.setEmail("daisyduck@gmail.com");
        customerOne.setGender(Gender.FEMALE);

        Customer customerTwo = new Customer();
        customerTwo.setCustomerId((long) 2);
        customerTwo.setFirstName("Donald");
        customerTwo.setLastName("Duck");
        customerTwo.setEmail("donaldduck@gmail.com");
        customerTwo.setGender(Gender.MALE);

        List <Customer> customerList = new ArrayList<>();
        customerList.add(customerOne);
        customerList.add(customerTwo);

        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void whenFindById_thenReturnCustomer(){
        Mockito.when(customerRepository.findById((long) 1))
                .thenReturn(Optional.of(customer));

        customerService.getCustomerById((long) 1);

        assertThat(customerService.getCustomerById((long)1).equals(customer));
    }

    @Test
    public void whenFindByIdNotFound_thenReturnNull(){
        Mockito.doReturn(null).when(customerRepository)
                .findById((long)100);

        Customer possbileCustomer = customerService.getCustomerById(customer.getCustomerId());

        assertNull(possbileCustomer, "Widget should not be found");
    }
    @Test
    public void whenAddCustomer_thenReturnCustomer(){
        Mockito.when(customerRepository.save(customer))
                .thenReturn(customer);
        assertThat(customerService.addCustomers(customer).equals(customer));
    }

}
