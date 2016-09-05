package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.Invoice;
import parking.repository.InvoiceRepository;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	@Autowired
	private InvoiceRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Invoice> findInvoices() {
		return repo.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Invoice findInvoiceById(@PathVariable Integer id) {
		return repo.findOne(id);
	}
}
