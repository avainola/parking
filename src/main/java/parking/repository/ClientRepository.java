package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parking.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
