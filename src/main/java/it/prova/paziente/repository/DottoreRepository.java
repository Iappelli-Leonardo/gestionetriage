package it.prova.paziente.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.paziente.model.Dottore;

public interface DottoreRepository extends PagingAndSortingRepository<Dottore, Long>, JpaSpecificationExecutor<Dottore> {

	public Dottore findBycodiceDipendete(String codiceInput);
	
}
