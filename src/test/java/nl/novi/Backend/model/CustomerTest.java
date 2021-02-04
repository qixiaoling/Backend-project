package nl.novi.Backend.model;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer customer;
    @Before
    public void setUp(){
        this.customer = new Customer("Mickey", "Mouse", Gender.MALE);
    }

    @Test
    public void testGetFirstName(){
        String expectedFirstName = "Mickey";
        String actualFirstName = this.customer.getFirstName();
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
    }
}
