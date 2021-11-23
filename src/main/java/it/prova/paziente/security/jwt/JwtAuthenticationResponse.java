package it.prova.paziente.security.jwt;

import java.util.Date;
import java.util.List;

public class JwtAuthenticationResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private String nome;
	private String cognome;
	private Date dataRegistrazione;
	private List<String> roles;

	public JwtAuthenticationResponse(String accessToken, String username, String nome, String cognome, Date dataRegistrazione, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dataRegistrazione = dataRegistrazione;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
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

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}