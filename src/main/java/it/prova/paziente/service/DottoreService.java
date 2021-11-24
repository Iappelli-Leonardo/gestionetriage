package it.prova.paziente.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.paziente.model.Dottore;

public interface DottoreService {

	public Dottore inserisciNuovo(Dottore transientInstance);

	public List<Dottore> listAll();

	public Dottore cariscaSingoloElemento(Long id);

	public Page<Dottore> searchAndPaginate(Dottore automobileExample, Integer pageNo, Integer pageSize, String sortBy);

	public Dottore get(Long idInput);

	public Dottore save(Dottore input);

	public void delete(Dottore input);
	
}
