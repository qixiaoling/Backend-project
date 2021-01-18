package nl.novi.Backend.model;

import javax.persistence.*;

@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @Column
    private Double totalPreTax;
    @Column
    private Double taxRate = 0.2;
    @Column
    private Double totalFee;
    @Column
    private Boolean invoiceSent;
    @Column
    private Boolean invoicePaid;

    //@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Customer customer;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Inspection inspection;

    public Invoice() {
    }

    public Invoice(Double totalPreTax, Double taxRate, Double totalFee,
                   Boolean invoiceSent, Boolean invoicePaid) {
        this.totalPreTax = totalPreTax;
        this.taxRate = taxRate;
        this.totalFee = totalFee;
        this.invoiceSent = invoiceSent;
        this.invoicePaid = invoicePaid;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTotalPreTax() {
        return totalPreTax;
    }

    public void setTotalPreTax(Double totalPreTax) {
        this.totalPreTax = totalPreTax;
    }

    public Double getTaxRate() {
        return taxRate;
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


    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }
}
