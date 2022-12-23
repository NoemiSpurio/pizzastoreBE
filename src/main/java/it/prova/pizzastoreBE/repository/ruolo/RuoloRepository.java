package it.prova.pizzastoreBE.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastoreBE.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);

}
