package it.prova.pizzastoreBE.repository.pizza;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastoreBE.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, CustomPizzaRepository {

}
