package nl.novi.Backend.service;

import nl.novi.Backend.model.CompositeKeyInvoice;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;


@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InspectionRepository inspectionRepository;
    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository=invoiceRepository;
    }

    public List<Invoice> getAllInvoices(){

        return invoiceRepository.findAll();
    }
    public ResponseEntity<?> addInvoicesToInspection(Long inspectionNumber, Invoice invoice){
        Optional <Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if(possibleInspection.isPresent()){
        Invoice inv = new Invoice();
        inv.setInvoicePK(new CompositeKeyInvoice(invoice.getInvoicePK().getCustomerId(), invoice.getInvoicePK().getInspectionNumber()));
        inv.setInvoicePaid(invoice.getInvoicePaid());
        inv.setInvoiceSent(invoice.getInvoiceSent());
        inv.setTaxRate(invoice.getTaxRate());
        inv.setTotalFee(invoice.getTotalFee());
        inv.setTotalPreTax(invoice.getTotalPreTax());
        inv.setInspection(possibleInspection.get());
        invoiceRepository.save(inv);
        return ResponseEntity.ok().body( new MessageResponse("The invoice is now added."));
        }
        return ResponseEntity.badRequest().body("Error, inspection does not exsits.");


        /*Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if(possibleInspection.isPresent()){
            invoice.setInspection(possibleInspection.get());
            invoiceRepository.save(invoice);
            return ResponseEntity.ok().body(new MessageResponse("Invoice is now added to this inspection."));
        }
        return ResponseEntity.badRequest().body(" The inspection cannot be found.");*/

    }
    public Invoice getInvoiceById(Long customerId, Long inspectionNumber){
        Optional <Invoice> possibleInvoice = invoiceRepository.findById(new CompositeKeyInvoice(customerId, inspectionNumber));
        if(possibleInvoice.isPresent()){
            return possibleInvoice.get();
        }
        return null;
    }


    }

