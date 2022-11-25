package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;

public class TrattaDTO {
	private Long id;
	@NotBlank(message = "{tratta.codice.notblank}")
	private String codice;
	@NotBlank(message = "{tratta.descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{tratta.data.notnull}")
	private LocalDate data;
	@NotNull(message = "{tratta.oradecollo.notnull}")
	private LocalTime oraDecollo;
	@NotNull(message = "{tratta.oraatterraggio.notnull}")
	private LocalTime oraAtterraggio;
	@NotNull(message = "{tratta.stato.notnull}")
	private Stato stato;
	
	@JsonIgnoreProperties(value = { "tratte" })
	private AirbusDTO airbus;
	
	public TrattaDTO() {
	}
	
	public TrattaDTO(Long id, String codice, String descrizione, LocalDate data, LocalTime oraDecollo, LocalTime oraAtterraggio, Stato stato) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOraDecollo() {
		return oraDecollo;
	}

	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}

	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}

	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public AirbusDTO getAirbusDTO() {
		return airbus;
	}

	public void setAirbusDTO(AirbusDTO airbus) {
		this.airbus = airbus;
	}
	
	public Tratta buildTrattaModel() {
		Tratta result = new Tratta(this.id, this.codice, this.descrizione, this.data, this.oraDecollo,
				this.oraAtterraggio, this.stato);
		if (this.airbus != null) {
			result.setAirbus(this.airbus.buildAirbusModel());
		}
		return result;
	}
	
	public static TrattaDTO buildTrattaDTOFromModel(Tratta trattaModel, boolean includeAirbus) {
		TrattaDTO result = new TrattaDTO(trattaModel.getId(), trattaModel.getCodice(), trattaModel.getDescrizione(),
				trattaModel.getData(), trattaModel.getOraDecollo(), trattaModel.getOraAtterraggio(),
				trattaModel.getStato());

		if (includeAirbus)
			result.setAirbusDTO(AirbusDTO.buildAirbusDTOFromModel(trattaModel.getAirbus(), false));

		return result;
	}
	
	public static List<TrattaDTO> createTrattaDTOListFromModelList(List<Tratta> modelListInput, boolean includeAirbus) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbus);
		}).collect(Collectors.toList());
	}
	
	public static Set<TrattaDTO> createTrattaDTOSetFromModelSet(Set<Tratta> modelListInput, boolean includeAirbus) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbus);
		}).collect(Collectors.toSet());
	}
}
