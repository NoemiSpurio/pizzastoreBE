package it.prova.pizzastoreBE.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastoreBE.model.Pizza;
import it.prova.pizzastoreBE.repository.pizza.PizzaRepository;

@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public List<Pizza> listAllPizze() {
		return (List<Pizza>) pizzaRepository.findAll();
	}

	@Override
	public Pizza caricaSingolaPizza(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		Pizza pizzaInstance = pizzaRepository.findById(idToRemove).orElse(null);

		pizzaInstance.setAttivo(false);

		pizzaRepository.save(pizzaInstance);
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		// TODO Auto-generated method stub
		return null;
	}

}
