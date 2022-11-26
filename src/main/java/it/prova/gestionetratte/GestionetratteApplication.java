package it.prova.gestionetratte;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.service.tratta.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner {

	@Autowired
	private TrattaService trattaService;

	@Autowired
	private AirbusService airbusService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Airbus airbus = new Airbus("codice1", "descrizione", LocalDate.of(2020, 8, 5), 100);
		airbusService.inserisci(airbus);

		Tratta tratta = new Tratta("codice1", "descrizione", LocalDate.of(2022, 12, 11), LocalTime.of(12, 30), LocalTime.of(14, 30), Stato.ATTIVO);
		tratta.setAirbus(airbus);
		trattaService.inserisci(tratta);

		Airbus airbus2 = new Airbus("codice2", "descrizione2", LocalDate.of(2021, 10, 1), 80);
		airbusService.inserisci(airbus2);
		
		Tratta tratta2 = new Tratta("codice2", "descrizione", LocalDate.of(2022, 12, 9), LocalTime.of(8, 30),
				LocalTime.of(11, 30), Stato.ATTIVO);
		tratta2.setAirbus(airbus2);
		trattaService.inserisci(tratta2);

	}

	/*
	 * ma che bella questa tastiera il layout è americano perché quando scrivo le e
	 * accentate va alla grande Balraj che cavolo stai guardando?
	 */

}
