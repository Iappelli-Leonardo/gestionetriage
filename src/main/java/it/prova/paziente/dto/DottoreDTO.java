package it.prova.paziente.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.paziente.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String cognome;

	@NotBlank
	private String codiceDipendete;
	
	private PazienteDTO paziente;

	public DottoreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendete, PazienteDTO paziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
		this.paziente = paziente;
	}

	public DottoreDTO(String nome, String cognome, String codiceDipendete, PazienteDTO paziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
		this.paziente = paziente;
	}

	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendete) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
	}

	public DottoreDTO(String nome, String cognome, String codiceDipendete) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
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

	public String getCodiceDipendete() {
		return codiceDipendete;
	}

	public void setCodiceDipendete(String codiceDipendete) {
		this.codiceDipendete = codiceDipendete;
	}

	public PazienteDTO getPaziente() {
		return paziente;
	}

	public void setPaziente(PazienteDTO paziente) {
		this.paziente = paziente;
	}
	
	public Dottore buildDottoreModel() {
		if(this.paziente == null) {
		 	return new Dottore(this.id,this.nome, this.cognome, this.codiceDipendete, null);
		}
		
		return new Dottore(this.id,this.nome, this.cognome, this.codiceDipendete, 
				this.paziente.buildPazienteModel());
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		if(dottoreModel.getPazienteAttualmenteInVisita() == null) {
			return new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(),dottoreModel.getCognome(),
				dottoreModel.getCodiceDipendete(),null);}
		
		return new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(),dottoreModel.getCognome(),
				dottoreModel.getCodiceDipendete(), PazienteDTO.buildPazienteDTOFromModel(dottoreModel.getPazienteAttualmenteInVisita()));
		
	}
	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreEntity);
			
			return result;
		}).collect(Collectors.toList());
	}

	

	
}
