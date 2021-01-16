package nl.novi.Backend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class CompositeKeyInvoice  implements Serializable {
    @Column
    private Long customerId;
    @Column
    private Long inspectionNumber;

    public CompositeKeyInvoice(Long customerId, Long inspectionNumber) {
        this.customerId = customerId;
        this.inspectionNumber = inspectionNumber;
    }

    public CompositeKeyInvoice() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(Long inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }
}
