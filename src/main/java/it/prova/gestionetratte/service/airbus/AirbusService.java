package it.prova.gestionetratte.service.airbus;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {
	List<Airbus> listAll();
	
	Airbus caricaSingoloElemento(Long id);
	
	Airbus caricaSingoloElementoEager(Long id);
	
	Airbus inserisci(Airbus input);
	
	Airbus aggiorna(Airbus input);
	
	void rimuovi(Long id);
	
	List<Airbus> findByExample(Airbus example);
}
