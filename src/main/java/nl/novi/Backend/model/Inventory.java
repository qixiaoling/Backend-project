package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NaturalIdCache
@EqualsAndHashCode
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Inventory {
    @Id
    @GeneratedValue
    private Long itemId;
    @NaturalId
    private String itemDescription;
    @Column
    private Double pricePerUnit;
    @Column
    private String manufacturer;
    @Column
    private int availableUnit;

    @JsonIgnore
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "inventory")
    private List<InspectionInventory> inspectionNewList = new ArrayList<>();

    public Inventory(){

    }

    public Inventory(String itemDescription, Double pricePerUnit, String manufacturer, int availableUnit,
                     List<InspectionInventory> inspectionNewList) {
        this.itemDescription = itemDescription;
        this.pricePerUnit = pricePerUnit;
        this.manufacturer = manufacturer;
        this.availableUnit = availableUnit;
        this.inspectionNewList = inspectionNewList;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufactor) {
        this.manufacturer = manufactor;
    }

    public int getAvailableUnit() {
        return availableUnit;
    }

    public void setAvailableUnit(int availableUnit) {
        this.availableUnit = availableUnit;
    }

    public List<InspectionInventory> getInspectionNewList() {
        return inspectionNewList;
    }

    public void setInspectionNewList(List<InspectionInventory> inspectionNewList) {
        this.inspectionNewList = inspectionNewList;
    }


}
