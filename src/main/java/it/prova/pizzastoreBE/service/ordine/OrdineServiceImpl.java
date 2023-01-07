package it.prova.pizzastoreBE.service.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastoreBE.model.Cliente;
import it.prova.pizzastoreBE.model.Ordine;
import it.prova.pizzastoreBE.model.Pizza;
import it.prova.pizzastoreBE.repository.ordine.OrdineRepository;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository ordineRepository;
	
	@Override
	public List<Ordine> listAllOrdini() {
		return (List<Ordine>) ordineRepository.findAll();
	}

	@Override
	public Ordine caricaSingoloOrdine(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}

	@Override
	public Ordine caricaSingoloOrdineEager(Long id) {
		return ordineRepository.findByIdEager(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Ordine ordineInstance) {
		int costoTotale = ordineInstance.getPizze().stream().collect(Collectors.summingInt(Pizza::getPrezzoBase));
		ordineInstance.setCostoTotale(costoTotale);
		ordineInstance.setClosed(false);
		ordineRepository.save(ordineInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		Ordine ordineInstance = ordineRepository.findById(idToRemove).orElse(null);
		
		ordineInstance.setClosed(true);
		
		ordineRepository.save(ordineInstance);
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		return ordineRepository.findByExample(example);
	}

	@Override
	public Integer ricaviTotaliTra(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.ricaviTotaliTra(dataInizio, dataFine);
	}

	@Override
	public Integer costiTotaliTra(LocalDate dataInizio, LocalDate dataFine) {
		final int costoPerPizza = 2;
		return ordineRepository.countPizzeOrderedBetween(dataInizio, dataFine) * costoPerPizza;
	}

	@Override
	public Integer ordiniTotaliTra(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.countByDataBetween(dataInizio, dataFine);
	}

	@Override
	public Integer totPizzeOrdinateTra(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.countPizzeOrderedBetween(dataInizio, dataFine);
	}

	@Override
	public List<Cliente> clientiVirtuosiTra(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.findAllClientiVirtuosiBetween(dataInizio, dataFine);
	}

}
