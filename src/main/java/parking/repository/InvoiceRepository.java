package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parking.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
