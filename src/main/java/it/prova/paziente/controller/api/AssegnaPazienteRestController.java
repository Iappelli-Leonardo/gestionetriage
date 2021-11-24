package it.prova.paziente.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import it.prova.paziente.model.Dottore;
import it.prova.paziente.model.Paziente;
import it.prova.paziente.service.DottoreService;
import it.prova.paziente.service.PazienteService;


@RestController
@RequestMapping(value = "/assegnapaziente", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssegnaPazienteRestController {
	
	@Autowired
	private DottoreService dottoreService;

	@Autowired
	private WebClient webClient;

	@PostMapping
	public void assegnaPaziente(@RequestParam String codiceFiscale, @RequestParam String codiceDipendente) {

		Paziente paziente = PazienteService.findByCodiceFiscale(codiceFiscale);
		Dottore dottore = dottoreService.findByCodiceDipendente(codiceDipendente);

		if (paziente == null)
			throw new PazienteNotFoundException("paziente non trovato");
		if (dottore == null)
			throw new DottoreNotFoundException("dottore non trovato");

		ResponseEntity<DottoreResponseDTO> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/verifica/{codiceDipendente}").build(codiceDipendente)).retrieve()
				.toEntity(DottoreResponseDTO.class).block();

		DottoreResponseDTO dottoreRicevuto = response.getBody();
		if (!dottoreRicevuto.isInServizio() || dottoreRicevuto.isInVisita())
			throw new DottoreOccupatoException("dottore non disponibil");

		ResponseEntity<DottoreResponseDTO> responseModifica = webClient.post().uri("/impostaInVisita")
				.body(Mono.just(new DottoreRequestDTO(dottore.getCodiceDipendente())), DottoreRequestDTO.class)
				.retrieve().toEntity(DottoreResponseDTO.class).block();

		if (responseModifica.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("Errore nella verifica!!!");

		paziente.setDottore(dottore);
		dottore.setPazienteAttualmenteInVisita(paziente);
		pazienteService.save(paziente);
		dottoreService.save(dottore);

		return;
	}
}