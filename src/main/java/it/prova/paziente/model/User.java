package it.prova.paziente.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "USERNAME", length = 50, unique = true)
	@NotNull
	@Size(min = 4, max = 50)
	private String username;

	@Column(name = "PASSWORD", length = 100)
	@NotNull
	@Size(min = 4, max = 100)
	private String password;

	@Column(name = "NOME", length = 100)
	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;
	
	@Column(name = "COGNOME", length = 100)
	@NotBlank
	@Size(min = 4, max = 100)
	private String cognome;
	
	@Column(name = "ENABLED")
	@NotNull
	private Boolean enabled;
	
	@Column(name = "DATAREGISTRAZIONE", length = 100)
	@NotNull
	private Date dataRegistrazione;
	
	@Enumerated(EnumType.STRING)
	private StatoUtente statoUtente;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_AUTHORITIES", joinColumns = {
			@JoinColumn(name = "USER_USERNAME", referencedColumnName = "USERNAME") }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID") })
	private List<Authority> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDataRegistrazaione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazaione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStatoUtente() {
		return statoUtente;
	}

	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
}

