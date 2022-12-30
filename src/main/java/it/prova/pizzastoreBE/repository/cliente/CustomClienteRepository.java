package it.prova.pizzastoreBE.repository.cliente;

import java.util.List;

import it.prova.pizzastoreBE.model.Cliente;

public interface CustomClienteRepository {
	
	List<Cliente> findByExample(Cliente example);
}
