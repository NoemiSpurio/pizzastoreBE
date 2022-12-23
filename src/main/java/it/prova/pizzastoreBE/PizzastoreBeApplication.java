package it.prova.pizzastoreBE;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastoreBE.model.Ruolo;
import it.prova.pizzastoreBE.model.Utente;
import it.prova.pizzastoreBE.service.ruolo.RuoloService;
import it.prova.pizzastoreBE.service.utente.UtenteService;

@SpringBootApplication
public class PizzastoreBeApplication implements CommandLineRunner {
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzastoreBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ADMIN_ROLE));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.PROPRIETARIO_ROLE));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.FATTORINO_ROLE));
		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Pluto", "Plutotto", new Date());
			pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
		
		if (utenteServiceInstance.findByUsername("proprietario") == null) {
			Utente proprietario = new Utente("proprietario", "proprietario", "Topolino", "Topolotto", new Date());
			proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE));
			utenteServiceInstance.inserisciNuovo(proprietario);
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}
		
		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Pluto", "Plutotto", new Date());
			fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE));
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.changeUserAbilitation(fattorino.getId());
		}
	}

}
