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

import it.prova.pizzastoreBE.dto.PizzaDTO;
import it.prova.pizzastoreBE.model.Pizza;
import it.prova.pizzastoreBE.service.pizza.PizzaService;
import it.prova.pizzastoreBE.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastoreBE.web.api.exception.PizzaNotFoundException;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public List<PizzaDTO> getAll() {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllPizze());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNew(@Valid @RequestBody PizzaDTO pizzaInput) {

		if (pizzaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		pizzaService.inserisciNuovo(pizzaInput.buildPizzaModel());
	}

	@GetMapping("/{id}")
	public PizzaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Pizza pizza = pizzaService.caricaSingolaPizza(id);

		if (pizza == null)
			throw new PizzaNotFoundException("Pizza not found con id: " + id);

		return PizzaDTO.buildPizzaDTOFromModel(pizza);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody PizzaDTO pizzaInput, @PathVariable(required = true) Long id) {
		Pizza pizza = pizzaService.caricaSingolaPizza(id);

		if (pizza == null)
			throw new PizzaNotFoundException("Pizza not found con id: " + id);

		pizzaInput.setId(id);
		pizzaService.aggiorna(pizzaInput.buildPizzaModel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id", required = true) long id) {
		Pizza pizza = pizzaService.caricaSingolaPizza(id);
		if (pizza == null)
			throw new PizzaNotFoundException("Pizza not found con id: " + id);
		pizzaService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<PizzaDTO> search(@RequestBody PizzaDTO example) {
		return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.findByExample(example.buildPizzaModel()));
	}
}
