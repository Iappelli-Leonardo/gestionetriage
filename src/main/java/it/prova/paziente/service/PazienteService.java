package it.prova.paziente.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.paziente.model.Paziente;

public interface PazienteService {
	
	public Paziente inserisciNuovo(Paziente transientInstance);

	public List<Paziente> listAll();

	public Paziente cariscaSingoloElemento(Long id);

	public Page<Paziente> searchAndPaginate(Paziente pazienteExample, Integer pageNo, Integer pageSize, String sortBy);

	public Paziente get(Long idInput);

	public Paziente save(Paziente input);

	public void delete(Paziente input);
	
	public Paziente findByCodiceFiscale(String codiceFiscale);
	
}
