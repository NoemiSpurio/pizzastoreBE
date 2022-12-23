package it.prova.pizzastoreBE.service.cliente;

import java.util.List;

import it.prova.pizzastoreBE.model.Cliente;

public interface ClienteService {

	public List<Cliente> listAllClienti();
	
	public Cliente caricaSingoloCliente(Long id);
	
	public void aggiorna(Cliente clienteInstance);

	public void inserisciNuovo(Cliente clienteInstance);

	public void rimuovi(Long idToRemove);

	public List<Cliente> findByExample(Cliente example);
}
