package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private String manufactor;
    @Column
    private int availableUnit;

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "inventory")
    private List<InspectionInventory> inspectionNewList = new ArrayList<>();

    public Inventory(){

    }

    public Inventory(String itemDescription, Double pricePerUnit, String manufactor, int availableUnit,
                     List<InspectionInventory> inspectionNewList) {
        this.itemDescription = itemDescription;
        this.pricePerUnit = pricePerUnit;
        this.manufactor = manufactor;
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

    public List<InspectionInventory> getInspectionNewList() {
        return inspectionNewList;
    }

    public void setInspectionNewList(List<InspectionInventory> inspectionNewList) {
        this.inspectionNewList = inspectionNewList;
    }


}
