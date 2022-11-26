package it.prova.gestionetratte.repository.aribus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>{
	
	@Query("from Airbus a left join fetch a.tratte t where a.id = :id")
	Airbus findByIdEager(Long id);
}
