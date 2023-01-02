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

import it.prova.pizzastoreBE.dto.ClienteDTO;
import it.prova.pizzastoreBE.dto.OrdineDTO;
import it.prova.pizzastoreBE.dto.StatisticheDTO;
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
	
	@PostMapping("/ricaviTotaliTra")
	public Integer ricaviTotaliTra(@Valid @RequestBody StatisticheDTO dateInput) {
		return ordineService.ricaviTotaliTra(dateInput.getDataInizio(), dateInput.getDataFine());
	}
	
	@PostMapping("/costiTotaliTra")
	public Integer costiTotaliTra(@Valid @RequestBody StatisticheDTO dateInput) {
		return ordineService.costiTotaliTra(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/ordiniTotaliTra")
	public Integer ordiniTotaliTra(@Valid @RequestBody StatisticheDTO dateInput) {
		return ordineService.ordiniTotaliTra(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/pizzeOrdinateTra")
	public Integer pizzeOrdinateTra(@Valid @RequestBody StatisticheDTO dateInput) {
		return ordineService.totPizzeOrdinateTra(dateInput.getDataInizio(), dateInput.getDataFine());
	}

	@PostMapping("/clientiVirtuosiTra")
	public List<ClienteDTO> clientiVirtuosiTra(@Valid @RequestBody StatisticheDTO dateInput) {
		return ClienteDTO.createClienteDTOListFromModelList(
				ordineService.clientiVirtuosiTra(dateInput.getDataInizio(), dateInput.getDataFine()));
	}
}
