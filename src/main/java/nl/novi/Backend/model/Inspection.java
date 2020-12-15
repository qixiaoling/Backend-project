package nl.novi.Backend.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Entity
@Table
public class Inspection {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column
    private int quantities;


    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = { @JoinColumn },
            inverseJoinColumns = {@JoinColumn}
    )
    private List<Inventory> inventoryList = new ArrayList<>();

    @OneToOne
    private Invoice invoice;

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Long getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(Long inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

}
