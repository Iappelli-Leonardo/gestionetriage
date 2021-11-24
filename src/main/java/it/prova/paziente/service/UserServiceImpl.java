package it.prova.paziente.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.paziente.exception.UserNotFoundException;
import it.prova.paziente.model.User;
import it.prova.paziente.repository.UserRepository;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User cariscaSingoloElemento(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User inserisciNuovo(User transientInstance) {
		return userRepository.save(transientInstance);
	}

	@Override
	public User get(Long idInput) {
		return userRepository.findById(idInput)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public User save(User input) {
		return userRepository.save(input);
	}

	@Override
	public void delete(User input) {
		userRepository.delete(input);
	}

	@Override
	public Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy) {

		Specification<User> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(userExample.getUsername()))
				predicates.add(
						cb.like(cb.upper(root.get("username")), "%" + userExample.getUsername().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + userExample.getEmail().toUpperCase() + "%"));

			if (userExample.getEnabled())
				predicates.add(cb.isTrue(root.get("enabled")));
			else if (userExample.getEnabled())
				predicates.add(cb.isFalse(root.get("enabled")));

			if (!StringUtils.isEmpty(userExample.getStato()))
				predicates.add(cb.like(cb.upper(root.get("stato")), "%" + userExample.getStato() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return userRepository.findAll(specificationCriteria, paging);
	}

}