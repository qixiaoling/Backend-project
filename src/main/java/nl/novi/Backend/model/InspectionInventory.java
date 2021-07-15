package nl.novi.Backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@EqualsAndHashCode
public class InspectionInventory {
    @EmbeddedId
    private InspectionInventoryId id;
    @Column
    private Integer inventoryQuantities;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("inspectionid")
    private Inspection inspection;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("inventoryid")
    private Inventory inventory;

    public InspectionInventory() {
    }

    public InspectionInventory(Inspection inspection, Inventory inventory) {
        this.inspection = inspection;
        this.inventory = inventory;
        this.id = new InspectionInventoryId(inspection.getInspectionNumber(), inventory.getItemId());
    }

    public InspectionInventoryId getId() {
        return id;
    }

    public void setId(InspectionInventoryId id) {
        this.id = id;
    }

    public Integer getInventoryQuantities() {
        return inventoryQuantities;
    }

    public void setInventoryQuantities(Integer inventoryQuantities) {
        this.inventoryQuantities = inventoryQuantities;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}


