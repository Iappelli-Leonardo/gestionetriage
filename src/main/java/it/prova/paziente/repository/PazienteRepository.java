package it.prova.paziente.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.paziente.model.Paziente;

public interface PazienteRepository  extends PagingAndSortingRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente> {

}