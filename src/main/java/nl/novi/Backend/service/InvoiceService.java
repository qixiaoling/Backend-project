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

    public ResponseEntity<?> createInvoice(Long inspectionNumber) {
        Double Sum = 0.0;
        Optional<Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if (possibleInspection.isPresent()) {
            if (possibleInspection.get().getAgreeToRepair().equals(Boolean.TRUE)) {
                Invoice aNewInvoice = new Invoice();
                for (int i = 0; i < possibleInspection.get().getInventoryNewList().size(); i++) {
                    Sum = possibleInspection.get().getInventoryNewList().get(i).getInventory().getPricePerUnit()
                            * possibleInspection.get().getInventoryNewList().get(i).getInventoryQuantities();
                }
                double totalPreTax = Sum + possibleInspection.get().getInspectionFee();
                double totalFee = ((possibleInspection.get().getInvoice().getTaxRate() + 1) * totalPreTax);
                aNewInvoice.setTotalFee(totalFee);
                aNewInvoice.setTotalPreTax(totalPreTax);
                invoiceRepository.save(aNewInvoice);
                return ResponseEntity.ok().body(new MessageResponse("Invoice including repair total fee is created."));
            }
            Invoice differentInvoice = new Invoice();
            differentInvoice.setTotalPreTax(possibleInspection.get().getInspectionFee());
            invoiceRepository.save(differentInvoice);
            return ResponseEntity.ok().body(new MessageResponse("Invoice with only inspection fee is created."));

        }
        return ResponseEntity.badRequest().body("Error, please check the inspection number again.");
    }


    /*public ResponseEntity<?> addInvoicesToInspection(Long inspectionNumber, Invoice invoice){
        Optional <Inspection> possibleInspection = inspectionRepository.findById(inspectionNumber);
        if(possibleInspection.isPresent()){
        invoice.setInspection(possibleInspection.get());
        invoiceRepository.save(invoice);
        return ResponseEntity.ok().body( new MessageResponse("The invoice is now added."));
        }
        return ResponseEntity.badRequest().body("Error, inspection does not exsits.");*/




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

