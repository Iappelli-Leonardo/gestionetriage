package it.prova.paziente.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.paziente.exception.DottoreNotFoundException;
import it.prova.paziente.model.Dottore;
import it.prova.paziente.repository.DottoreRepository;

@Service
public class DottoreServiceImpl implements DottoreService{
	
	@Autowired
	private DottoreRepository dottoreRepository;

	@Override
	public List<Dottore> listAll() {
		return (List<Dottore>) dottoreRepository.findAll();
	}

	@Override
	public Dottore cariscaSingoloElemento(Long id) {
		return dottoreRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Dottore inserisciNuovo(Dottore transientInstance) {
		return dottoreRepository.save(transientInstance);
	}

	@Override
	public Dottore get(Long idInput) {
		return dottoreRepository.findById(idInput)
				.orElseThrow(() -> new DottoreNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public Dottore save(Dottore input) {
		return dottoreRepository.save(input);
	}

	@Override
	public void delete(Dottore input) {
		dottoreRepository.delete(input);
	}

	@Override
	public Page<Dottore> searchAndPaginate(Dottore dottoreExample, Integer pageNo, Integer pageSize, String sortBy) {

		Specification<Dottore> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(dottoreExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + dottoreExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(dottoreExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("cognome")), "%" + dottoreExample.getCognome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(dottoreExample.getCodiceDipendete()))
				predicates.add(cb.like(cb.upper(root.get("codiceDipendente")),
						"%" + dottoreExample.getCodiceDipendete().toUpperCase() + "%"));

			if (dottoreExample.getPazienteAttualmenteInVisita() != null
					&& dottoreExample.getPazienteAttualmenteInVisita().getId() != null)
				predicates.add(cb.like(cb.upper(root.get("pazienteAttualmenteInVisita")),
						"%" + dottoreExample.getPazienteAttualmenteInVisita() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return dottoreRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public Dottore findByCodiceDipendente(String codiceInput) {

		return dottoreRepository.findByCodiceDipendente(codiceInput);
	}

}