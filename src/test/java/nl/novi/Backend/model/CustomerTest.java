package nl.novi.Backend.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
class CustomerTest {

    private Customer customer;
    @BeforeEach
    void setUp(){

        this.customer = new Customer("Mickey", "Mouse", Gender.MALE);
    }

    @Test
    void testGetFirstName(){
        String expectedFirstName = "Mickey";
        String actualFirstName = this.customer.getFirstName();
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
    }
}
