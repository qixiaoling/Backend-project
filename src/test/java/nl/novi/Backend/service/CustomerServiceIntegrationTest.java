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
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        when(customerRepository.findById((long) 1))
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
        when(customerRepository.save(customer))
                .thenReturn(customer);
        assertThat(customerService.addCustomers(customer).equals(customer));
    }
    @Test
    public void deleteCusotmer_thenVerified(){
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        //Give instruction, findbyid, please just return a possible customer back.
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        customerService.deleteCustomerById((long)1);
        verify(customerRepository, times(1)).deleteById((long)1);
    }

    @Test
    public void deleteCusotmer_thenVerifiedNotPresent(){
        //Give instruction, let it skip the step of "find the possible customer", directly go to in CustomerService
        // let it skip the main part and say it is not present.
        customerService.deleteCustomerById((long)1);
        verify(customerRepository, times(1)).findById((long)1);
    }

}
