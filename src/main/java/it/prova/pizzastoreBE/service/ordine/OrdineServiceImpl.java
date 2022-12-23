package it.prova.pizzastoreBE.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastoreBE.model.Ordine;
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
		// TODO Auto-generated method stub
		return null;
	}

}
