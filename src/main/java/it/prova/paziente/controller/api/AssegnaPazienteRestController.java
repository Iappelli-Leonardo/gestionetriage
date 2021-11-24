package it.prova.paziente.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.paziente.dto.DottoreRequestDTO;
import it.prova.paziente.dto.DottoreResponceDTO;
import it.prova.paziente.exception.DottoreNotFoundException;
import it.prova.paziente.exception.DottoreOccupatoException;
import it.prova.paziente.exception.PazienteNotFoundException;
import it.prova.paziente.model.Dottore;
import it.prova.paziente.model.Paziente;
import it.prova.paziente.service.DottoreService;
import it.prova.paziente.service.PazienteService;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/assegnapaziente", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssegnaPazienteRestController {
	
	@Autowired
	private PazienteService pazienteService;
	
	@Autowired
	private DottoreService dottoreService;

	@Autowired
	private WebClient webClient;

	@PostMapping
	public void assegnaPaziente(@RequestParam String codiceFiscale, @RequestParam String codiceDipendente) {

		Paziente paziente = pazienteService.findByCodiceFiscale(codiceFiscale);
		Dottore dottore = dottoreService.findByCodiceDipendente(codiceDipendente);

		if (paziente == null)
			throw new PazienteNotFoundException("paziente non trovato!");
		if (dottore == null)
			throw new DottoreNotFoundException("dottore non trovato!");

		ResponseEntity<DottoreResponceDTO> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/verifica/{codiceDipendente}").build(codiceDipendente)).retrieve()
				.toEntity(DottoreResponceDTO.class).block();

		DottoreResponceDTO dottoreRicevuto = response.getBody();
		if (!dottoreRicevuto.isInServizio() || dottoreRicevuto.isInVisita())
			throw new DottoreOccupatoException("dottori occupati!");

		ResponseEntity<DottoreResponceDTO> responseModifica = webClient.post().uri("/impostaInVisita")
				.body(Mono.just(new DottoreRequestDTO(dottore.getCodiceDipendete())), DottoreRequestDTO.class)
				.retrieve().toEntity(DottoreResponceDTO.class).block();
		
		if (responseModifica.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("Errore nella verifica!!!");

		paziente.setDottore(dottore);
		dottore.setPazienteAttualmenteInVisita(paziente);
		pazienteService.save(paziente);
		dottoreService.save(dottore);

		return;
	}
}