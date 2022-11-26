package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.tratta.TrattaService;

@RestController
@RequestMapping("api/tratta")
public class TrattaController {

	@Autowired
	private TrattaService trattaService;
	
	@GetMapping
	public List<TrattaDTO> listAll(){
		return TrattaDTO.createTrattaDTOListFromModelList(trattaService.listAll(), true);
	}
	
	@GetMapping("/{id}")
	public TrattaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Tratta trattaEsiste = trattaService.caricaSingoloElementoEager(id);
		if(trattaEsiste == null)//da gestire con custom
			throw new RuntimeException("tratta non trovata");
		
		return TrattaDTO.buildTrattaDTOFromModel(trattaEsiste, true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrattaDTO insert(@Valid @RequestBody TrattaDTO tratta) {
		if(tratta.getId() != null)//da gestire con custom
			throw new RuntimeException("impossibile inserire un elemente che ha già un id");
		
		Tratta trattaInserita = trattaService.inserisci(tratta.buildTrattaModel());
		return TrattaDTO.buildTrattaDTOFromModel(trattaInserita, true);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public TrattaDTO update(@Valid @RequestBody TrattaDTO tratta) {
		Tratta trattaDaAgiornare = trattaService.caricaSingoloElemento(tratta.getId());
		
		if(trattaDaAgiornare == null)//da gestire con custom
			throw new RuntimeException("tratta non trovata");
		
		Tratta trattaAggiornata = trattaService.aggiorna(tratta.buildTrattaModel());
		return TrattaDTO.buildTrattaDTOFromModel(trattaAggiornata, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id", required = true) Long id) {
		Tratta trattaEsiste = trattaService.caricaSingoloElementoEager(id);
		if(trattaEsiste == null)//da gestire con custom
			throw new RuntimeException("tratta non trovata");
		
		trattaService.rimuovi(id);
	}
}
