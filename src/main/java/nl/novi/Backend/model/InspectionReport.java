package nl.novi.Backend.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
@IdClass(CompositeKeyInspectionReport.class)
public class InspectionReport {

    @Id
    private Long inspectionNumber;
    @Id
    private Long executedItem;
    @Column
    private Boolean agreeToRepair;
    @Column
    private int unit;
    @Column
    private Date repairDate;
    @Column
    private Boolean repairComplete;
    @Column
    private Double feePerExecutedItem;
    @ManyToOne (fetch = FetchType.LAZY)
    private Inventory inventory;
    @ManyToOne (fetch = FetchType.LAZY)
    private Inspection inspection;



    public InspectionReport(){

    }

    public InspectionReport(Long inspectionNumber, Long executedItem, Boolean agreeToRepair, int unit, Date repairDate, Boolean repairComplete, Double feePerExecutedItem) {
        this.inspectionNumber = inspectionNumber;
        this.executedItem = executedItem;
        this.agreeToRepair = agreeToRepair;
        this.unit = unit;
        this.repairDate = repairDate;
        this.repairComplete = repairComplete;
        this.feePerExecutedItem = feePerExecutedItem;
    }

    public Long getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(Long inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public Long getExecutedItem() {
        return executedItem;
    }

    public void setExecutedItem(Long executedItem) {
        this.executedItem = executedItem;
    }

    public Boolean getAgreeToRepair() {
        return agreeToRepair;
    }

    public void setAgreeToRepair(Boolean agreeToRepair) {
        this.agreeToRepair = agreeToRepair;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
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

    public Double getFeePerExecutedItem() {
        return feePerExecutedItem;
    }

    public void setFeePerExecutedItem(Double feePerExecutedItem) {
        this.feePerExecutedItem = feePerExecutedItem;
    }
}


