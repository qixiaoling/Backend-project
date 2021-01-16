package nl.novi.Backend.model;


import javax.persistence.*;
import java.util.*;
import java.util.List;

@Entity
@Table
public class Inspection {
@Id
@GeneratedValue
    private Long inspectionNumber;
    @Column
    private Date inspectionDate;
    @Column
    private Boolean inspectionResult;
    @Column
    private Boolean inspectionComplete;
    @Column
    private Double inspectionFee;
    @Column
    private Boolean agreeToRepair;
    @Column
    private Date repairDate;
    @Column
    private Boolean repairComplete;



    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;

    @OneToMany (orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "inspection")
    private List<InspectionInventory> inspectionInventoryList = new ArrayList<>();

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inspection")
    private Invoice invoice;

    public Inspection() {
    }

    public Inspection(Date inspectionDate, Boolean inspectionResult, Boolean inspectionComplete,
                      Double inspectionFee, Boolean agreeToRepair, Date repairDate, Boolean repairComplete, Car car,
                      List<InspectionInventory> inspectionInventoryList, Invoice invoice) {
        this.inspectionDate = inspectionDate;
        this.inspectionResult = inspectionResult;
        this.inspectionComplete = inspectionComplete;
        this.inspectionFee = inspectionFee;
        this.agreeToRepair = agreeToRepair;
        this.repairDate = repairDate;
        this.repairComplete = repairComplete;
        this.car = car;
        this.inspectionInventoryList = inspectionInventoryList;
        this.invoice = invoice;
    }

    public Long getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(Long inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Boolean getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(Boolean inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public Boolean getInspectionComplete() {
        return inspectionComplete;
    }

    public void setInspectionComplete(Boolean inspectionComplete) {
        this.inspectionComplete = inspectionComplete;
    }

    public Double getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(Double inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

    public Boolean getAgreeToRepair() {
        return agreeToRepair;
    }

    public void setAgreeToRepair(Boolean agreeToRepair) {
        this.agreeToRepair = agreeToRepair;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Boolean getRepairComplete() {
        return repairComplete;
    }

    public void setRepairComplete(Boolean repairComplete) {
        this.repairComplete = repairComplete;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<InspectionInventory> getInspectionInventoryList() {
        return inspectionInventoryList;
    }

    public void setInspectionInventoryList(List<InspectionInventory> inspectionInventoryList) {
        this.inspectionInventoryList = inspectionInventoryList;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
