package nl.novi.Backend.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long executedItem;
    @Column
    private String executedDescription;
    @Column
    private Double pricePerUnit;
    @Column
    private String manufactor;
    @Column
    private int availableUnit;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inventory")
    private List<InspectionReport> inspectionsReport;

    public Inventory(){

    }

    public Inventory(Long executedItem, String executedDescription, Double pricePerUnit, String manufactor, int availableUnit, List<InspectionReport> inspectionsReport) {
        this.executedItem = executedItem;
        this.executedDescription = executedDescription;
        this.pricePerUnit = pricePerUnit;
        this.manufactor = manufactor;
        this.availableUnit = availableUnit;
        this.inspectionsReport = inspectionsReport;
    }

    public Long getExecutedItem() {
        return executedItem;
    }

    public void setExecutedItem(Long executedItem) {
        this.executedItem = executedItem;
    }

    public String getExecutedDescription() {
        return executedDescription;
    }

    public void setExecutedDescription(String executedDescription) {
        this.executedDescription = executedDescription;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public int getAvailableUnit() {
        return availableUnit;
    }

    public void setAvailableUnit(int availableUnit) {
        this.availableUnit = availableUnit;
    }

    public List<InspectionReport> getInspectionsReport() {
        return inspectionsReport;
    }

    public void setInspectionsReport(List<InspectionReport> inspectionsReport) {
        this.inspectionsReport = inspectionsReport;
    }
}
