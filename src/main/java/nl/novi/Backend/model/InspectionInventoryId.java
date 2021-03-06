package nl.novi.Backend.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
public class InspectionInventoryId implements Serializable {
    @Column
    private Long inspectionid;
    @Column
    private Long inventoryid;

    public InspectionInventoryId() {
    }

    public InspectionInventoryId(Long inspectionid, Long inventoryid) {
        this.inspectionid = inspectionid;
        this.inventoryid = inventoryid;
    }

    public Long getInspectionid() {
        return inspectionid;
    }

    public Long getInventoryid() {
        return inventoryid;
    }

}
