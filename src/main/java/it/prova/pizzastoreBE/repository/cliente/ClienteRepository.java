package it.prova.pizzastoreBE.repository.cliente;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastoreBE.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, CustomClienteRepository {

}
