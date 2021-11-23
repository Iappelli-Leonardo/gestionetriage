package it.prova.paziente.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.paziente.model.Paziente;
import it.prova.paziente.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String cognome;

	@NotBlank
	private String codiceFiscale;
	
	@NotNull
	private Date dataRegistrazione;

	private StatoPaziente stato;
	
	private DottoreDTO dottore;

	public PazienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente stato, DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.dottore = dottore;
	}
	
	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione,
			StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
	}

	public PazienteDTO(String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente stato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoPaziente getStato() {
		return stato;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}

	public DottoreDTO getDottore() {
		return dottore;
	}

	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}
	
	public Paziente buildPazienteModel() {
		return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione, this.stato, this.dottore.buildDottoreModel());
	}
	
	public static PazienteDTO buildPazienteDTOFromModel(Paziente paziente) {
		return new PazienteDTO(paziente.getId(), paziente.getNome(), paziente.getCognome(),
				paziente.getCodiceFiscale(), paziente.getDataRegistrazione(),paziente.getStato(),
				DottoreDTO.buildDottoreDTOFromModel(paziente.getDottore()));
	}
	
	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(dottoreEntity);
			return result;
		}).collect(Collectors.toList());
	}
	
	
	

}
