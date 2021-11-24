package it.prova.paziente;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.paziente.model.Authority;
import it.prova.paziente.model.AuthorityName;
import it.prova.paziente.model.StatoUtente;
import it.prova.paziente.model.User;
import it.prova.paziente.security.repository.AuthorityRepository;
import it.prova.paziente.security.repository.UserRepository;
import it.prova.paziente.service.PazienteService;

@SpringBootApplication
public class Application{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initAutomobili(PazienteService pazienteService) {
		return (args) -> {

			// verifico inserimento
			System.out.println("Elenco Pazienti");
			pazienteService.listAll().forEach(autoItem -> {
				System.out.println(autoItem);
			});

			User user = userRepository.findByUsername("admin").orElse(null);

			if (user == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityAdmin = new Authority();
				authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
				authorityAdmin = authorityRepository.save(authorityAdmin);

				Authority authorityUser = new Authority();
				authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
				authorityUser = authorityRepository.save(authorityUser);

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityAdmin, authorityUser });

				user = new User();
				user.setAuthorities(authorities);
				user.setEnabled(true);
				user.setUsername("admin");
				user.setNome("admin");
				user.setCognome("admin");
				user.setPassword(passwordEncoder.encode("admin"));
				user.setDataCreazione(new Date());
				user.setStato(StatoUtente.ATTIVO);
				user = userRepository.save(user);
			}

			User commonUser = userRepository.findByUsername("commonUser").orElse(null);

			if (commonUser == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityUser = authorityRepository.findByName(AuthorityName.ROLE_SUB_OPERATOR);
				if (authorityUser == null) {
					authorityUser = new Authority();
					authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
					authorityUser = authorityRepository.save(authorityUser);
				}

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityUser });

				commonUser = new User();
				commonUser.setAuthorities(authorities);
				commonUser.setEnabled(true);
				commonUser.setUsername("commonUser");
				commonUser.setNome("paziente1");
				commonUser.setCognome("cognome1");
				commonUser.setPassword(passwordEncoder.encode("commonUser"));
				commonUser.setDataCreazione(new Date());
				commonUser.setStato(StatoUtente.ATTIVO);
				commonUser = userRepository.save(commonUser);
			}
		};

	}

}
