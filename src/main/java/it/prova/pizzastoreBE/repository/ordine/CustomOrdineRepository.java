package it.prova.pizzastoreBE.repository.ordine;

import java.util.List;

import it.prova.pizzastoreBE.model.Ordine;

public interface CustomOrdineRepository {

	List<Ordine> findByExample(Ordine example);

}
