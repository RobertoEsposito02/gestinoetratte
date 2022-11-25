package it.prova.gestionetratte.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.Airbus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTO {

	private Long id;
	@NotBlank(message = "{airbus.codice.notblank}")
	private String codice;
	@NotBlank(message = "{airbus.descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{airbus.datainizioservizio.notnull}")
	private LocalDate dataInizioServizio;
	@Positive(message = "{airbus.numeropasseggeri.negative}")
	private Integer numeroPassaggeri;
	
	@JsonIgnoreProperties(value = { "airbus" })
	private List<TrattaDTO> tratte = new ArrayList<>();

	public AirbusDTO() {
	}
	
	public AirbusDTO(Long id, String codice, String descrizione, LocalDate dataInizioServizio, Integer numeroPasseggeri) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPassaggeri = numeroPasseggeri;
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

	public LocalDate getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(LocalDate dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public Integer getNumeroPassaggeri() {
		return numeroPassaggeri;
	}

	public void setNumeroPassaggeri(Integer numeroPassaggeri) {
		this.numeroPassaggeri = numeroPassaggeri;
	}

	public List<TrattaDTO> getTratte() {
		return tratte;
	}

	public void setTratte(List<TrattaDTO> tratte) {
		this.tratte = tratte;
	}

	public Airbus buildAirbusModel() {
		return new Airbus(this.id,this.codice,this.descrizione,this.dataInizioServizio,this.numeroPassaggeri);
	}
	
	public static AirbusDTO buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
		AirbusDTO result = new AirbusDTO(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
				airbusModel.getDataInizioServizio(), airbusModel.getNumeroPassaggeri());
		if (includeTratte)
			result.setTratte(TrattaDTO.createTrattaDTOListFromModelList(airbusModel.getTratte(), false));
		return result;
	}

	public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> modelListInput, boolean includeTratte) {
		return modelListInput.stream().map(airbusEntity -> {
			AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(airbusEntity, includeTratte);
			if (includeTratte)
				result.setTratte(TrattaDTO.createTrattaDTOListFromModelList(airbusEntity.getTratte(), false));
			return result;
		}).collect(Collectors.toList());
	}
}
