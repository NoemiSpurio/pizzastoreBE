package it.prova.pizzastoreBE.service.pizza;

import java.util.List;

import it.prova.pizzastoreBE.model.Pizza;

public interface PizzaService {

	public List<Pizza> listAllPizze();

	public Pizza caricaSingolaPizza(Long id);

	public void aggiorna(Pizza pizzaInstance);

	public void inserisciNuovo(Pizza pizzaInstance);

	public void rimuovi(Long idToRemove);

	public List<Pizza> findByExample(Pizza example);
}
