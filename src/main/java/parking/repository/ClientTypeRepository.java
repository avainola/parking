package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parking.model.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {

}
