package it.prova.paziente.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dottore")
public class Dottore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDipendete;
	@OneToOne
	private Paziente pazienteAttualmenteInVisita;

	public Dottore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dottore(Long id, String nome, String cognome, String codiceDipendete, Paziente pazienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	public Dottore(Long id, String nome, String cognome, String codiceDipendete) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
	}

	public Dottore(String nome, String cognome, String codiceDipendete, Paziente pazienteAttualmenteInVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendete = codiceDipendete;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	public Dottore(String nome, String cognome, String codiceDipendete) {
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

	public Paziente getPazienteAttualmenteInVisita() {
		return pazienteAttualmenteInVisita;
	}

	public void setPazienteAttualmenteInVisita(Paziente pazienteAttualmenteInVisita) {
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}
}
