package nl.novi.Backend.controller;


import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService=invoiceService;
    }

    @GetMapping("/invoices")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'USER_BAC', 'USER_TRE', 'USER_TEC', 'ADMIN')")
    public ResponseEntity<?> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/invoices/generate/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public ResponseEntity<?> generateInvoiceByInspectionNumber(@PathVariable("inspectionNumber") Long inspectionNumber){
        return invoiceService.getInvoiceByInspectionNumber(inspectionNumber);
    }
    @GetMapping("/invoices/{invoiceId}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'USER_BAC', 'USER_TRE', 'USER_TEC', 'ADMIN')")
    public Invoice getInvoiceById( @PathVariable ("invoiceId") Long invoiceId){

        return invoiceService.getInvoiceById(invoiceId);
    }
    @PutMapping("/invoices/{invoiceId}")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public ResponseEntity<?> updateInvoiceById(@PathVariable ("invoiceId") Long invoiceId,
                                               @RequestBody Invoice aNewInvoice){
        return invoiceService.updateInvoiceById(invoiceId, aNewInvoice);
    }
    @DeleteMapping("/invoices/{invoiceId}")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public ResponseEntity<?> deleteInvoiceById(@PathVariable ("invoiceId") Long invoiceId){
        return invoiceService.deleteInvoiceById(invoiceId);
    }
}
