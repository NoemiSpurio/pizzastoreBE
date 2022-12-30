package it.prova.pizzastoreBE.repository.pizza;

import java.util.List;

import it.prova.pizzastoreBE.model.Pizza;

public interface CustomPizzaRepository {

	List<Pizza> findByExample(Pizza example);
}
