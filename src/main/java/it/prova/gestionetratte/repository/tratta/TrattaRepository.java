package it.prova.gestionetratte.repository.tratta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>, CustomTrattaRepository{
	
	@Query("from Tratta t left join fetch t.airbus a where t.id = :id")
	Optional<Tratta> findByIdEager(Long id);
	
	@Query("from Tratta t where t.stato = 'ATTIVO' and t.data < curdate() ")
	List<Tratta> concludiTratte();
}
