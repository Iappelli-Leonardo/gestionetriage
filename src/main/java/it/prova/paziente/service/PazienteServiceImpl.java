package it.prova.paziente.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import it.prova.paziente.exception.PazienteNotFoundException;
import it.prova.paziente.model.Paziente;
import it.prova.paziente.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService{

	@Autowired
	private PazienteRepository pazienteRepository;
	
	@Transactional
	public Paziente inserisciNuovo(Paziente transientInstance) {
		return pazienteRepository.save(transientInstance);
	}

	@Transactional
	public List<Paziente> listAll() {
		return (List<Paziente>) pazienteRepository.findAll();
	}

	@Transactional
	public Paziente cariscaSingoloElemento(Long id) {
		return pazienteRepository.findById(id).orElse(null);
	}

	@Transactional
	public Page<Paziente> searchAndPaginate(Paziente Example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Paziente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(Example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + Example.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(Example.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("cognome")), "%" + Example.getCognome().toUpperCase() + "%"));

			if (Example.getDataRegistrazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("DataRegistrazione"),
						Example.getDataRegistrazione()));
			
			if (!StringUtils.isEmpty(Example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")),
						"%" + Example.getCodiceFiscale().toUpperCase() + "%"));
			

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return pazienteRepository.findAll(specificationCriteria, paging);
	}

	@Transactional
	public Paziente get(Long idInput) {
		return pazienteRepository.findById(idInput)
				.orElseThrow(() -> new PazienteNotFoundException("Element with id " + idInput + " not found."));
	}

	@Transactional
	public Paziente save(Paziente input) {
		return pazienteRepository.save(input);
	}

	@Transactional
	public void delete(Paziente input) {
		pazienteRepository.delete(input);
	}

}
