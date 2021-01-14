package nl.novi.Backend.model;

import javax.persistence.*;

@Entity
@Table
public class Invoice {
    @EmbeddedId
    private CompositeKeyInvoice invoicePK;
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

    public Invoice() {
    }

    public Invoice(CompositeKeyInvoice invoicePK, Double totalPreTax, int taxRate, Double totalFee,
                   Boolean invoiceSent, Boolean invoicePaid, Customer customer, Inspection inspection) {
        this.invoicePK = invoicePK;
        this.totalPreTax = totalPreTax;
        this.taxRate = taxRate;
        this.totalFee = totalFee;
        this.invoiceSent = invoiceSent;
        this.invoicePaid = invoicePaid;
        this.customer = customer;
        this.inspection = inspection;
    }

    public CompositeKeyInvoice getInvoicePK() {
        return invoicePK;
    }

    public void setInvoicePK(CompositeKeyInvoice invoicePK) {
        this.invoicePK = invoicePK;
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
