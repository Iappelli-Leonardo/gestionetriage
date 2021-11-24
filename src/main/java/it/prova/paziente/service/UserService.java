package it.prova.paziente.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.paziente.model.User;

public interface UserService {

	
	public User inserisciNuovo(User transientInstance);

	public List<User> listAll();

	public User cariscaSingoloElemento(Long id);

	public Page<User> searchAndPaginate(User automobileExample, Integer pageNo, Integer pageSize, String sortBy);

	public User get(Long idInput);

	public User save(User input);

	public User aggiorna(User input);
	
	public void changeUserAbilitation(Long userInstanceId);
	
}
