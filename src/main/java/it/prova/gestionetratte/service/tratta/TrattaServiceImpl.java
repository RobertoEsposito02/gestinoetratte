package it.prova.gestionetratte.service.tratta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService{

	@Autowired
	private TrattaRepository repository;
	
	@Override
	public List<Tratta> listAll() {
		return (List<Tratta>) repository.findAll();
	}

	@Override
	public Tratta caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tratta caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

	@Override
	@Transactional
	public Tratta inserisci(Tratta input) {
		return repository.save(input);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta input) {
		return repository.save(input);
	}

	@Override
	@Transactional
	public void rimuovi(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		return repository.findByExample(example);
	}

}
