package nl.novi.Backend.service;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InspectionService {

    private InspectionRepository inspectionRepository;

    @Autowired
    public InspectionService(InspectionRepository inspectionRepository){
        this.inspectionRepository=inspectionRepository;
    }

    public List<Inspection> getAllInspection(){
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.findAll().forEach(inspections::add);
        return inspections;
    }

    public List<Inspection> addInspection(Inspection inspection){
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.save(inspection);
        return inspections;
    }

}
