package nl.novi.Backend.service;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.InspectionReport;
import nl.novi.Backend.repo.InspectionReportRepository;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
@Service
public class InspectionReportService {
    private InspectionReportRepository inspectionReportRepository;

    @Autowired
    public InspectionReportService(InspectionReportRepository inspectionReportRepository){
        this.inspectionReportRepository=inspectionReportRepository;
    }

    public List<InspectionReport> getAllInspectionReports(){
        List<InspectionReport> inspectionReports = new ArrayList<>();
        inspectionReportRepository.findAll().forEach(inspectionReports::add);
        return inspectionReports;

    }

    public List<InspectionReport> addInspectionReports(InspectionReport inspectionReport){
        List<InspectionReport> inspectionReports = new ArrayList<>();
        inspectionReportRepository.save(inspectionReport);
    return inspectionReports;
    }
}
