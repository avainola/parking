package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parking.model.ParkingHouse;

public interface ParkingHouseRepository extends JpaRepository<ParkingHouse, Integer> {

}
