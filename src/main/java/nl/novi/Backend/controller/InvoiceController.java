package nl.novi.Backend.controller;

import nl.novi.Backend.model.CompositeKeyInvoice;
import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService=invoiceService;
    }

    @GetMapping("/invoices")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/invoices/{inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public ResponseEntity<?> addInvoicesToInspection(@PathVariable ("inspectionNumber") Long inspectionNumber,
                                                     @RequestBody Invoice invoice){

        return invoiceService.addInvoicesToInspection(inspectionNumber, invoice);
    }
    @GetMapping("/invoices/{customerId, inspectionNumber}")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public Invoice getInvoiceById(@PathVariable("customerId") CompositeKeyInvoice invoicePK, ){
        return invoiceService.getInvoiceById(invoicePK);
    }
}
