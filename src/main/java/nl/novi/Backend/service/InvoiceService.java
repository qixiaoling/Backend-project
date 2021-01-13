package nl.novi.Backend.service;

import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository=invoiceRepository;
    }
    public List<Invoice> getAllInvoices(){
        List<Invoice> invoices = new ArrayList<>();
        invoiceRepository.findAll().forEach(invoices::add);
        return invoices;
    }
    public List<Invoice> addInvoices(Invoice invoice){
        List<Invoice> invoices = new ArrayList<>();
        invoiceRepository.save(invoice);
            return invoices;
        }


    }

