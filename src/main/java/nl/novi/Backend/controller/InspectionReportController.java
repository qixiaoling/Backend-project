package nl.novi.Backend.controller;

import nl.novi.Backend.model.InspectionReport;
import nl.novi.Backend.repo.InspectionReportRepository;
import nl.novi.Backend.service.InspectionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class InspectionReportController {

    private InspectionReportService inspectionReportService;

    @Autowired
    public InspectionReportController(InspectionReportService inspectionReportService){
        this.inspectionReportService= inspectionReportService;
    }

    @GetMapping("/inspectionReports")
    public List<InspectionReport> getAllInspectionReports(){
        List<InspectionReport> inspectionReports = new ArrayList<>();
        return inspectionReportService.getAllInspectionReports();
    }

    @PostMapping("/inspectionReports")
    public List<InspectionReport> addInspectionReports(@RequestBody InspectionReport inspectionReport){
        return inspectionReportService.addInspectionReports(inspectionReport);
    }


}
