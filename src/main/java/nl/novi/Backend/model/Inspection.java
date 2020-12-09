package nl.novi.Backend.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private String numberPlate;
    @Column
    private Boolean inspectionResult;
    @Column
    private Boolean inspectionComplete;
    @Column
    private Double inspectionFee;

    @ManyToOne (fetch = FetchType.LAZY)
    private Car car;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InspectionReport> inspectionsReport;
    @OneToOne
    private Invoice invoice;


    public Inspection(){

    }

    public Inspection(Long inspectionNumber, Date inspectionDate, String numberPlate, Boolean inspectionResult,
                      Boolean inspectionComplete, Double inspectionFee, Car car,
                      List<InspectionReport> inspectionsReport, Invoice invoice) {
        this.inspectionNumber = inspectionNumber;
        this.inspectionDate = inspectionDate;
        this.numberPlate = numberPlate;
        this.inspectionResult = inspectionResult;
        this.inspectionComplete = inspectionComplete;
        this.inspectionFee = inspectionFee;
        this.car = car;
        this.inspectionsReport = inspectionsReport;
        this.invoice=invoice;
    }

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

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
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

    public List<InspectionReport> getInspectionsReport() {
        return inspectionsReport;
    }

    public void setInspectionsReport(List<InspectionReport> inspectionsReport) {
        this.inspectionsReport = inspectionsReport;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
