package it.prova.gestionetratte.service.tratta;

import java.util.List;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {
	List<Tratta> listAll();
	
	Tratta caricaSingoloElemento(Long id);
	
	Tratta caricaSingoloElementoEager(Long id);
	
	Tratta inserisci(Tratta input);
	
	Tratta aggiorna(Tratta input);
	
	void rimuovi(Long id);
	
	List<Tratta> findByExample(Tratta example);
}
