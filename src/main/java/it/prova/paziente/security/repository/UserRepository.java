package it.prova.paziente.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.prova.paziente.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

}