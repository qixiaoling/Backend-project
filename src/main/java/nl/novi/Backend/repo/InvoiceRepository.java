package nl.novi.Backend.repo;

import nl.novi.Backend.model.CompositeKeyInvoice;
import nl.novi.Backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, CompositeKeyInvoice>{
}
