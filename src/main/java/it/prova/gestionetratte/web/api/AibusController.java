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

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.dto.AirbusDTOSovrapposizioni;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.web.api.exception.AirbusConTratteException;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/airbus")
public class AibusController {
	
	@Autowired
	private AirbusService airbusService;
	
	@GetMapping
	public List<AirbusDTO> listAll(){
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAll(), true);
	}
	
	@GetMapping("/{id}")
	public AirbusDTO findById(@PathVariable(name = "id", required = true) Long id) {
		Airbus airbus = airbusService.caricaSingoloElementoEager(id);
		
		if(airbus == null)
			throw new AirbusNotFoundException("airbus non trovato");
		
		return AirbusDTO.buildAirbusDTOFromModel(airbus, true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AirbusDTO insert(@Valid @RequestBody AirbusDTO airbus) {
		if(airbus.getId() != null)
			throw new IdNotNullForInsertException("impossibile inserire un elemente che ha già un id");
		
		Airbus airbusInserito = airbusService.inserisci(airbus.buildAirbusModel());
		return AirbusDTO.buildAirbusDTOFromModel(airbusInserito, true);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public AirbusDTO update(@Valid @RequestBody AirbusDTO airbus) {
		Airbus airbusDaAggiornare = airbusService.caricaSingoloElemento(airbus.getId());
		
		if(airbusDaAggiornare == null)
			throw new AirbusNotFoundException("airbus non trovato");
		
		Airbus airbusAggiornato = airbusService.aggiorna(airbus.buildAirbusModel());
		return AirbusDTO.buildAirbusDTOFromModel(airbusAggiornato, true);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id", required = true) Long id) {
		Airbus airbus = airbusService.caricaSingoloElementoEager(id);
		
		if(airbus == null)
			throw new AirbusNotFoundException("airbus non trovato");
		
		if(airbus.getTratte() != null && airbus.getTratte().size() > 0)
			throw new AirbusConTratteException("impossibile eliminare un parent con dei figli associati");
		
		airbusService.rimuovi(id);
	}
	
	@GetMapping("/search")
	public List<AirbusDTO> findByExample(@RequestBody AirbusDTO example){
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.findByExample(example.buildAirbusModel()),
				true);
	}
	
	@GetMapping("/listaAirbusEvidenziandoSovrapposizioni")
	public List<AirbusDTOSovrapposizioni> listaAirbusEvidenziandoSovrapposizioni(){
		return airbusService.findListaAirbusEvidenziandoSovrapposizioni();
	}
}
