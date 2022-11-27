package it.prova.gestionetratte.repository.aribus;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface CustomAirbusRepository {
	List<Airbus> findByExample(Airbus example);
}
