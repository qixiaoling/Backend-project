package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Car {
    @Id
    private String numberPlate;
    @Column
    private String make;
    @Column
    private String model;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;



    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private List<Inspection> inspections;

    public Car(){

    }

    @JsonBackReference
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car(String numberPlate, String make, String model) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //@JsonManagedReference
    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }
}
