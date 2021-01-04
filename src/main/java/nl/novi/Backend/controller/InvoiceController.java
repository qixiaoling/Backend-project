package nl.novi.Backend.controller;

import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/invoices")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }
    @GetMapping("/invoices")
    @PreAuthorize("hasAnyAuthority('USER_TRE','ADMIN')")
    public List<Invoice> addInvoices(Invoice invoice){
       return invoiceService.addInvoices(invoice);
    }
}
