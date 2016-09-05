package parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import parking.model.Client;
import parking.model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {

	@Query("SELECT p FROM Parking p WHERE p.client = :client AND p.invoice IS NULL")
    public List<Parking> findAllUninvoicedParkings(@Param("client") Client client);
	
}
