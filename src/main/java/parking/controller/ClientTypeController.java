package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.ClientType;
import parking.repository.ClientTypeRepository;

@RestController
@RequestMapping("/clientTypes")
public class ClientTypeController {
	@Autowired
	private ClientTypeRepository clientTypeRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<ClientType> findClientTypes() {
		return clientTypeRepo.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ClientType findCLientTypeById(@PathVariable Integer id) {
		return clientTypeRepo.findOne(id);
	}
}
