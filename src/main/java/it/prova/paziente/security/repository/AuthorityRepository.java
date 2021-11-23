package it.prova.paziente.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.prova.paziente.model.Authority;
import it.prova.paziente.model.AuthorityName;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}