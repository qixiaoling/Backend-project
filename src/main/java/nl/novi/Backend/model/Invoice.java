package nl.novi.Backend.model;

import javax.persistence.*;

@Entity
@Table@IdClass(CompositeKeyInvoice.class)
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "invoice")
    private Customer customer;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "invoice")
    private Inspection inspection;





}
