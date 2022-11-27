package it.prova.gestionetratte.service.airbus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.aribus.AirbusRepository;

@Service
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
		return repository.findByIdEager(id);
	}

	@Override
	@Transactional
	public Airbus inserisci(Airbus input) {
		return repository.save(input);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus input) {
		return repository.save(input);
	}

	@Override
	@Transactional
	public void rimuovi(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		return repository.findByExample(example);
	}

}
