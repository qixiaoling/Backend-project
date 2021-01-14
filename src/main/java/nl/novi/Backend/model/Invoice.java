package nl.novi.Backend.model;

import javax.persistence.*;

@Entity
@Table
@IdClass(CompositeKeyInvoice.class)
public class Invoice {
    @Id
    private Long customerId;
    @Id
    private Long inspectionNumber;
    @Column
    private Double totalPreTax;
    @Column
    private int taxRate;
    @Column
    private Double totalFee;
    @Column
    private Boolean invoiceSent;
    @Column
    private Boolean invoicePaid;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Inspection inspection;

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

    public Double getTotalPreTax() {
        return totalPreTax;
    }

    public void setTotalPreTax(Double totalPreTax) {
        this.totalPreTax = totalPreTax;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Boolean getInvoiceSent() {
        return invoiceSent;
    }

    public void setInvoiceSent(Boolean invoiceSent) {
        this.invoiceSent = invoiceSent;
    }

    public Boolean getInvoicePaid() {
        return invoicePaid;
    }

    public void setInvoicePaid(Boolean invoicePaid) {
        this.invoicePaid = invoicePaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

}
