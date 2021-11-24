package it.prova.paziente.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.paziente.exception.UserNotFoundException;
import it.prova.paziente.model.StatoUtente;
import it.prova.paziente.model.User;
import it.prova.paziente.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional
	public User cariscaSingoloElemento(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + id + " not found."));
	}

	@Transactional
	public Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<User> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(userExample.getUsername()))
				predicates.add(
						cb.like(cb.upper(root.get("USERNAME")), "%" + userExample.getUsername().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("NOME")), "%" + userExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("COGNOME")), "%" + userExample.getCognome().toUpperCase() + "%"));

			if (userExample.getDataCreazione() != null)
				predicates
						.add(cb.greaterThanOrEqualTo((root.get("DATACREAZIONE")),userExample.getDataCreazione()));

			if (userExample.getStato() != null)
				predicates.add(cb.like(cb.upper(root.get("STATO")), "%" + userExample.getStato() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return userRepository.findAll(specificationCriteria, paging);
	}

	@Transactional
	public User aggiorna(User userInstance) {
		return userRepository.save(userInstance);

	}

	@Transactional
	public User inserisciNuovo(User userInstance) {
		userInstance.setDataCreazione(new Date());
	return	userRepository.save(userInstance);

	}

	@Transactional
	public void changeUserAbilitation(Long userInstanceId) {
		
		User userInstance = cariscaSingoloElemento(userInstanceId);
		
		if(userInstance.getStato().equals(StatoUtente.ATTIVO)) {
			userInstance.setStato(StatoUtente.DISABILITATO);
		}
		if(userInstance.getStato().equals(StatoUtente.CREATO)) {
			userInstance.setStato(StatoUtente.DISABILITATO);
		}
	}

	@Transactional
	public User get(Long idInput) {
		return userRepository.findById(idInput)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + idInput + " not found."));
	}

	@Transactional
	public User save(User input) {
		input.setDataCreazione(new Date());
		input.setStato(StatoUtente.CREATO);
		return userRepository.save(input);
	}


}