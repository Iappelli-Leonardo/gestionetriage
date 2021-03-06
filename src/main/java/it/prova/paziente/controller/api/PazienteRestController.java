package it.prova.paziente.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.prova.paziente.exception.PazienteInVisitaException;
import it.prova.paziente.model.Paziente;
import it.prova.paziente.model.StatoPaziente;
import it.prova.paziente.service.PazienteService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api/paziente", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PazienteRestController {
	
	@Autowired
	private PazienteService pazienteService;

	@GetMapping("/{idInput}")
	public Paziente getPaziente(@PathVariable(required = true) Long idInput) {
		return pazienteService.get(idInput);
	}

	@GetMapping
	public List<Paziente> getAll() {
		return pazienteService.listAll();
	}

	@PostMapping("/search")
	public ResponseEntity<Page<Paziente>> searchAndPagination(@RequestBody Paziente pazienteExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		Page<Paziente> results = pazienteService.searchAndPaginate(pazienteExample, pageNo, pageSize, sortBy);

		return new ResponseEntity<Page<Paziente>>(results, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public Paziente createNewPaziente(@RequestBody Paziente pazienteInput) {
		pazienteInput.setStato(StatoPaziente.IN_ATTESA_VISITA);
		return pazienteService.save(pazienteInput);
	}

	@PutMapping("/{id}")
	public Paziente updatePaziente(@RequestBody Paziente pazienteInput, @PathVariable Long id) {
		Paziente pazienteToUpdate = pazienteService.get(id);
		pazienteToUpdate.setNome(pazienteInput.getNome());
		pazienteToUpdate.setCognome(pazienteInput.getCognome());
		pazienteToUpdate.setCodiceFiscale(pazienteInput.getCodiceFiscale());
		pazienteToUpdate.setDataRegistrazione(pazienteInput.getDataRegistrazione());
		pazienteToUpdate.setStato(pazienteInput.getStato());
		pazienteToUpdate.setDottore(pazienteInput.getDottore());
		return pazienteService.save(pazienteToUpdate);
	}

	@DeleteMapping("/{id}")
	public void deletePaziente(@PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.cariscaSingoloElemento(id);
		if (paziente.getStato().equals(StatoPaziente.DISMESSO))
		pazienteService.delete(pazienteService.get(id));
		else 
			throw new PazienteInVisitaException("il paziente ?? ancora in visita!");
	}
	

}
