package nl.novi.Backend.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer customer;
    @BeforeEach
    public void setUp(){
        this.customer = new Customer("Mickey", "Mouse", Gender.MALE, "mickeymouse@gmail");
    }

    @Test
    public void testGetFirstName(){
        String expectedFirstName = "Mickey";
        String actualFirstName = this.customer.getFirstName();
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
    }
}
