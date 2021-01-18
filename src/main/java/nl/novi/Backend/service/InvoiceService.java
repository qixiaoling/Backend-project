package nl.novi.Backend.service;

import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.model.Invoice;
import nl.novi.Backend.payload.response.MessageResponse;
import nl.novi.Backend.repo.InspectionRepository;
import nl.novi.Backend.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        invoice.setInspection(possibleInspection.get());
        invoiceRepository.save(invoice);
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
    public Invoice getInvoiceById(Long invoiceId){
        Optional <Invoice> possibleInvoice = invoiceRepository.findById(invoiceId);
        if(possibleInvoice.isPresent()){
            return possibleInvoice.get();
        }
        return null;
    }

    public ResponseEntity<?> updateInvoiceById(Long invoiceId, Invoice aNewInvoice){
        Optional <Invoice> possibleInvoice = invoiceRepository.findById(invoiceId);
        if(possibleInvoice.isPresent()){
            if(!(aNewInvoice.getInvoicePaid()==null)){
                possibleInvoice.get().setInvoicePaid(aNewInvoice.getInvoicePaid());
            }
            if(!(aNewInvoice.getTotalPreTax()==null)){
                possibleInvoice.get().setTotalPreTax(aNewInvoice.getTotalPreTax());
            }
            if(!(aNewInvoice.getTaxRate()==null)){
                possibleInvoice.get().setTaxRate(aNewInvoice.getTaxRate());
            }
            if(!(aNewInvoice.getTotalFee()==null)){
                possibleInvoice.get().setTotalFee(aNewInvoice.getTotalFee());
            }
            if(!(aNewInvoice.getInvoiceSent()==null)){
                possibleInvoice.get().setInvoiceSent(aNewInvoice.getInvoiceSent());
            }
            return ResponseEntity.ok().body(new MessageResponse("The invoice is successfully updated."));


        }
        return ResponseEntity.badRequest().body("Error, please check the invoice ID again.");

    }

    public ResponseEntity<?> deleteInvoiceById(Long invoiceId){
        Optional <Invoice> possibleInvoice = invoiceRepository.findById(invoiceId);
        if(possibleInvoice.isPresent()){
            invoiceRepository.deleteById(invoiceId);
            return ResponseEntity.ok().body(new MessageResponse("The invoice is successfully deleted."));
        }
        return ResponseEntity.badRequest().body("Error, please check the invoice ID again");


    }


    }

