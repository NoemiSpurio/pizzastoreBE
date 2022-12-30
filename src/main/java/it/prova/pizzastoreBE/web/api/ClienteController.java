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
import it.prova.pizzastoreBE.model.Cliente;
import it.prova.pizzastoreBE.service.cliente.ClienteService;
import it.prova.pizzastoreBE.web.api.exception.ClienteNotFoundException;
import it.prova.pizzastoreBE.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<ClienteDTO> getAll() {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllClienti());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNew(@Valid @RequestBody ClienteDTO clienteInput) {

		if (clienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		clienteService.inserisciNuovo(clienteInput.buildClienteModel());
	}

	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFoundException("Cliente not found con id: " + id);

		return ClienteDTO.buildClienteDTOFromModel(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id", required = true) long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFoundException("Cliente not found con id: " + id);

		clienteService.rimuovi(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody ClienteDTO clienteInput, @PathVariable(required = true) Long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFoundException("Cliente not found con id: " + id);

		clienteInput.setId(id);
		clienteService.aggiorna(clienteInput.buildClienteModel());
	}

	@PostMapping("/search")
	public List<ClienteDTO> search(@RequestBody ClienteDTO example) {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.findByExample(example.buildClienteModel()));
	}
}
