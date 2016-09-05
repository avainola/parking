package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.PriceSchema;
import parking.repository.PriceSchemaRepository;

@RestController
@RequestMapping("/priceSchemas")
public class PriceSchemaController {
	@Autowired
	private PriceSchemaRepository priceSchemaRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<PriceSchema> findPriceSchemas() {
		return priceSchemaRepo.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PriceSchema findPriceSchemaById(@PathVariable Integer id) {
		return priceSchemaRepo.findOne(id);
	}
	
}
