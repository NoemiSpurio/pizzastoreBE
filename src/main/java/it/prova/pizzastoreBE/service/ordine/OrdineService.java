package it.prova.pizzastoreBE.service.ordine;

import java.util.List;

import it.prova.pizzastoreBE.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAllOrdini();

	public Ordine caricaSingoloOrdine(Long id);

	public Ordine caricaSingoloOrdineEager(Long id);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);

	public List<Ordine> findByExample(Ordine example);
}
