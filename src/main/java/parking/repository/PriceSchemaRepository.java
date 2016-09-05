package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parking.model.PriceSchema;

public interface PriceSchemaRepository extends JpaRepository<PriceSchema, Integer> {

}
