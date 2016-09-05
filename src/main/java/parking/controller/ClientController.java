package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.Client;
import parking.model.Invoice;
import parking.model.Parking;
import parking.repository.ClientRepository;
import parking.repository.InvoiceRepository;
import parking.repository.ParkingRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ParkingRepository parkingRepo;
	@Autowired
	private InvoiceRepository invoiceRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Client> findClients() {
		return clientRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Client addClient(@RequestBody Client client) {
		client.setId(null);
		return clientRepo.saveAndFlush(client);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Client findClientById(@PathVariable Integer id) {
		return clientRepo.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Client updateClient(@RequestBody Client updatedClient, @PathVariable Integer id) {
		updatedClient.setId(id);
		return clientRepo.saveAndFlush(updatedClient);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteClient(@PathVariable Integer id) {
		clientRepo.delete(id);
	}
	
	@RequestMapping(value = "/{id}/parkings", method = RequestMethod.GET)
	public List<Parking> findParkingsForClient(@PathVariable Integer id) {
		Client client = clientRepo.findOne(id);
		return client.getParkings();
	}
	
	@RequestMapping(value = "/{id}/parkings", method = RequestMethod.POST)
	public Parking addParkingForClient(@RequestBody Parking newParking, @PathVariable Integer id) {
		Client client = clientRepo.findOne(id);
		newParking.setClient(client);
		return parkingRepo.saveAndFlush(newParking);
	}
	
	@RequestMapping(value = "/{id}/invoices", method = RequestMethod.GET)
	public List<Invoice> findInvoicesForClient(@PathVariable Integer id) {
		Client client = clientRepo.findOne(id);
		return client.getInvoices();
	}
	
	@RequestMapping(value = "/{id}/invoices", method = RequestMethod.POST)
	public Invoice addInvoice(@PathVariable Integer id) {
		Client client = clientRepo.findOne(id);
		Invoice invoice = new Invoice(client, parkingRepo.findAllUninvoicedParkings(client));
		invoice.setId(null);
		return invoiceRepo.saveAndFlush(invoice);
	}
	
}
