package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.JoinColumn;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;
    @Column
    @Email
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private Car car;
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    //private List<Invoice> invoiceList = new ArrayList<>();




    public Customer(){

    };

    public Customer(String firstName, String lastName, Gender gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonManagedReference
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
