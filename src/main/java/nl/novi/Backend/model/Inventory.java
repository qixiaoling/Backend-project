package nl.novi.Backend.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long item;
    @Column
    private String itemDescription;
    @Column
    private Double pricePerUnit;
    @Column
    private String manufactor;
    @Column
    private int availableUnit;
    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inventoryList")
    private List<Inspection> inspectionList = new ArrayList<>();

    public Inventory(){

    }

    public Inventory(Long item, String itemDescription, Double pricePerUnit, String manufactor, int availableUnit) {
        this.item = item;
        this.itemDescription = itemDescription;
        this.pricePerUnit = pricePerUnit;
        this.manufactor = manufactor;
        this.availableUnit = availableUnit;

    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public int getAvailableUnit() {
        return availableUnit;
    }

    public void setAvailableUnit(int availableUnit) {
        this.availableUnit = availableUnit;
    }

    public List<Inspection> getInspectionList() {
        return inspectionList;
    }

    public void setInspectionList(List<Inspection> inspectionList) {
        this.inspectionList = inspectionList;
    }
}
