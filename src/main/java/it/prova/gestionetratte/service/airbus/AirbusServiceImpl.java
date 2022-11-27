package it.prova.gestionetratte.service.airbus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.dto.AirbusDTOSovrapposizioni;
import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.aribus.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {

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

	@Override
	public List<AirbusDTOSovrapposizioni> findListaAirbusEvidenziandoSovrapposizioni() {
		List<AirbusDTOSovrapposizioni> listAirbusEager = AirbusDTOSovrapposizioni
				.createAirbusDTOListFromModelList(repository.findAllEager(), true);
		for (AirbusDTOSovrapposizioni airbusItem : listAirbusEager) {
			for (int i = 0; i < airbusItem.getTratte().size()-1; i++) {
				for (int j = i+1; j < airbusItem.getTratte().size(); j++ ){
					/* controllo che la data sia la stessa */
					if(airbusItem.getTratte().get(j).getData().equals(airbusItem.getTratte().get(i).getData())) {
						/* controllo se l'ora di decollo sono uguali */
						if(airbusItem.getTratte().get(j).getOraDecollo().equals(airbusItem.getTratte().get(i).getOraDecollo())) {
							airbusItem.setConSovrapposizioni(true);
						}
						/* controllo se l'ora di decollo della prima tratta è successiva a quella delle tratte successive */
						else if(airbusItem.getTratte().get(i).getOraDecollo().isAfter(airbusItem.getTratte().get(j).getOraDecollo())) {
							/* controllo se l'ora di decollo della prima tratta è precedente a quella di atterraggio delle tratte succ */
							if(airbusItem.getTratte().get(i).getOraDecollo().isBefore(airbusItem.getTratte().get(j).getOraAtterraggio())) {
								airbusItem.setConSovrapposizioni(true);
							}
						}
						/* controllo se l'ora di atterraggio sono uguali */
						else if(airbusItem.getTratte().get(j).getOraAtterraggio().equals(airbusItem.getTratte().get(i).getOraAtterraggio())) {
							airbusItem.setConSovrapposizioni(true);
						}
						/* controllo se l'ora di atterraggio della prima tratta NON è precedente all'ora di decollo delle altre tratte */
						else if(!airbusItem.getTratte().get(j).getOraAtterraggio().isBefore(airbusItem.getTratte().get(i).getOraDecollo())) {
							airbusItem.setConSovrapposizioni(true);
						}
					}
				}
			}
		}

		return listAirbusEager;
	}

}
