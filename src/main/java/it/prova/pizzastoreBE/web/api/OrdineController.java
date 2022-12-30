package it.prova.pizzastoreBE.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastoreBE.dto.OrdineDTO;
import it.prova.pizzastoreBE.model.Ordine;
import it.prova.pizzastoreBE.service.ordine.OrdineService;
import it.prova.pizzastoreBE.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastoreBE.web.api.exception.OrdineNotFoundException;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;

	@GetMapping
	public List<OrdineDTO> getAll() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllOrdini());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNew(@Valid @RequestBody OrdineDTO ordineInput) {

		if (ordineInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		ordineService.inserisciNuovo(ordineInput.buildOrdineModel());

	}

	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);
		if (ordine == null)
			throw new OrdineNotFoundException("Ordine not found con id: " + id);
		return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody OrdineDTO ordineInput,
			@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);
		if (ordine == null)
			throw new OrdineNotFoundException("Ordine not found con id: " + id);
		ordineInput.setId(id);
		ordineService.aggiorna(ordineInput.buildOrdineModel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);
		if (ordine == null)
			throw new OrdineNotFoundException("Pizza not found con id: " + id);
		ordineService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<OrdineDTO> search(@RequestBody OrdineDTO example) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findByExample(example.buildOrdineModel()));
	}
}
