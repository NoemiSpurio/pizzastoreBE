package it.prova.pizzastoreBE.service.ordine;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastoreBE.model.Cliente;
import it.prova.pizzastoreBE.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAllOrdini();

	public Ordine caricaSingoloOrdine(Long id);

	public Ordine caricaSingoloOrdineEager(Long id);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);

	public List<Ordine> findByExample(Ordine example);
	
	public Integer ricaviTotaliTra(LocalDate dataInizio, LocalDate dataFine);
	
	public Integer costiTotaliTra(LocalDate dataInizio, LocalDate dataFine);

	public Integer ordiniTotaliTra(LocalDate dataInizio, LocalDate dataFine);

	public Integer totPizzeOrdinateTra(LocalDate dataInizio, LocalDate dataFine);

	public List<Cliente> clientiVirtuosiTra(LocalDate dataInizio, LocalDate dataFine);
	
	public List<Ordine> ordiniPerFattorino(String username);
}
