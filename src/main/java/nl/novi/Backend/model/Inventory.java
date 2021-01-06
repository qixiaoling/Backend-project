package nl.novi.Backend.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
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

    public Inventory(Long itemId, String itemDescription, Double pricePerUnit, String manufactor, int availableUnit) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.pricePerUnit = pricePerUnit;
        this.manufactor = manufactor;
        this.availableUnit = availableUnit;

    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
