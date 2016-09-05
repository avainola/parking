package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.model.Parking;
import parking.repository.ParkingRepository;

@RestController
@RequestMapping("/parkings")
public class ParkingController {
	@Autowired
	private ParkingRepository parkingRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Parking> findParkings() {
		return parkingRepo.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Parking findParkingById(@PathVariable Integer id) {
		return parkingRepo.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Parking updateParking(@RequestBody Parking updatedParking, @PathVariable Integer id) {
		updatedParking.setId(id);
		return parkingRepo.saveAndFlush(updatedParking);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteParking(@PathVariable Integer id) {
		parkingRepo.delete(id);
	}
	
}
