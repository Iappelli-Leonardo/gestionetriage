package it.prova.paziente.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paziente")
public class Paziente {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataRegistrazione;
	private Dottore dottore;
	
	@Enumerated(EnumType.STRING)
	private StatoPaziente statoPaziente;

	public Paziente(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione, Dottore dottore,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.dottore = dottore;
		this.statoPaziente = statoPaziente;
	}

	public Paziente(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dataRegistrazione, Dottore dottore,
			StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.dottore = dottore;
		this.statoPaziente = statoPaziente;
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dataRegistrazione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
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

	public Dottore getDottore() {
		return dottore;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	public StatoPaziente getStato() {
		return statoPaziente;
	}

	public void setStato(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}
	

}
