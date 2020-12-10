package nl.novi.Backend.repo;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.InspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionReportRepository  extends JpaRepository <InspectionReport, Long> {
}
