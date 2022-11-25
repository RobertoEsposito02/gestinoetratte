package it.prova.gestionetratte.service.airbus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.aribus.AirbusRepository;

public class AirbusServiceImpl implements AirbusService{

	@Autowired
	private AirbusRepository repository;
	
	@Override
	public List<Airbus> listAll() {
		return (List<Airbus>) repository.findAll();
	}

	@Override
	public Airbus caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Airbus caricaSingoloElementoEager(Long id) {
		return null;
	}

	@Override
	public Airbus inserisci(Airbus input) {
		return repository.save(input);
	}

	@Override
	public Airbus aggiorna(Airbus input) {
		return repository.save(input);
	}

	@Override
	public void rimuovi(Long id) {
		repository.deleteById(id);
	}

}
