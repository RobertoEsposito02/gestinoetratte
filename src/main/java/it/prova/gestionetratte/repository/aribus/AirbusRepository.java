package it.prova.gestionetratte.repository.aribus;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>, CustomAirbusRepository{
	
	@Query("from Airbus a left join fetch a.tratte t where a.id = :id")
	Airbus findByIdEager(Long id);
	
	@Query("select distinct a from Airbus a left join fetch a.tratte")
	List<Airbus> findAllEager();
}
