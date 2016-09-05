package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.ParkingHouse;
import parking.repository.ParkingHouseRepository;

@RestController
@RequestMapping("/parkingHouses")
public class ParkingHouseController {
	@Autowired
	private ParkingHouseRepository parkingHouseRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<ParkingHouse> findParkingHouses() {
		return parkingHouseRepo.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ParkingHouse findParkingHouseById(@PathVariable Integer id) {
		return parkingHouseRepo.findOne(id);
	}

}
