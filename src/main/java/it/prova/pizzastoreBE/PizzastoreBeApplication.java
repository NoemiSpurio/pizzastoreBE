package it.prova.pizzastoreBE;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastoreBE.model.Cliente;
import it.prova.pizzastoreBE.model.Ordine;
import it.prova.pizzastoreBE.model.Pizza;
import it.prova.pizzastoreBE.model.Ruolo;
import it.prova.pizzastoreBE.model.Utente;
import it.prova.pizzastoreBE.service.cliente.ClienteService;
import it.prova.pizzastoreBE.service.ordine.OrdineService;
import it.prova.pizzastoreBE.service.pizza.PizzaService;
import it.prova.pizzastoreBE.service.ruolo.RuoloService;
import it.prova.pizzastoreBE.service.utente.UtenteService;

@SpringBootApplication
public class PizzastoreBeApplication implements CommandLineRunner {
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PizzaService pizzaServiceInstance;
	@Autowired
	private ClienteService clienteServiceInstance;
	@Autowired
	private OrdineService ordineServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzastoreBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.ROLE_PROPRIETARIO));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.ROLE_FATTORINO));
		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Pluto", "Plutotto", new Date());
			pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
		
		if (utenteServiceInstance.findByUsername("proprietario") == null) {
			Utente proprietario = new Utente("proprietario", "proprietario", "Topolino", "Topolotto", new Date());
			proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
			utenteServiceInstance.inserisciNuovo(proprietario);
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}
		
		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Pluto", "Plutotto", new Date());
			fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.changeUserAbilitation(fattorino.getId());
		}
		
		if (utenteServiceInstance.findByUsername("super") == null) {
			Utente superuser = new Utente("super", "super", "Miglior", "Migliorotto", new Date());
			superuser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
			superuser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
			superuser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			superuser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(superuser);
			utenteServiceInstance.changeUserAbilitation(superuser.getId());
		}
		
		Pizza margherita = new Pizza("margherita", "pomodoro, mozzarella", 5, true);
		Pizza crostino = new Pizza("crostino", "cotto, mozzarella", 6, true);
		Pizza diavola = new Pizza("diavola", "pomodoro, mozzarella, salame piccante", 7, false);
		Pizza porcini = new Pizza("porcini", "porcini, mozzarella", 8, true);
		Pizza ultracostosa = new Pizza("costosissima", "oro", 150, true);
		pizzaServiceInstance.inserisciNuovo(margherita);
		pizzaServiceInstance.inserisciNuovo(crostino);
		pizzaServiceInstance.inserisciNuovo(diavola);
		pizzaServiceInstance.inserisciNuovo(porcini);
		pizzaServiceInstance.inserisciNuovo(ultracostosa);
		
		Cliente clienteAttivo = new Cliente("Pinco", "Pallino", "via brubru 12", true);
		Cliente clienteNonAttivo = new Cliente("Tizio", "Caio", "via brubru 12", false);
		Cliente clienteAttivo2 = new Cliente("Pinchetto", "Palletto", "via brubru 12", true);
		clienteServiceInstance.inserisciNuovo(clienteNonAttivo);
		clienteServiceInstance.inserisciNuovo(clienteAttivo);
		clienteServiceInstance.inserisciNuovo(clienteAttivo2);
		
		Ordine ordine = new Ordine("ABC123", LocalDate.now(), 13, false, clienteAttivo);
		ordine.getPizze().add(margherita);
		ordine.getPizze().add(porcini);
		ordine.setFattorino(utenteServiceInstance.findByUsername("fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine);
		
		Ordine ordine2 = new Ordine("CDF543", LocalDate.now(), 13, false, clienteAttivo);
		ordine2.getPizze().add(margherita);
		ordine2.getPizze().add(porcini);
		ordine2.getPizze().add(crostino);
		ordine2.setFattorino(utenteServiceInstance.findByUsername("fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine2);
		
		Ordine ordine3 = new Ordine("IOP908", LocalDate.now(), 13, false, clienteAttivo);
		ordine3.getPizze().add(ultracostosa);
		ordine3.getPizze().add(porcini);
		ordine3.setFattorino(utenteServiceInstance.findByUsername("fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine3);
		
		Ordine ordine4 = new Ordine("IOP908", LocalDate.now(), 13, false, clienteAttivo2);
		ordine4.getPizze().add(ultracostosa);
		ordine4.getPizze().add(porcini);
		ordine4.setFattorino(utenteServiceInstance.findByUsername("fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine4);
	}

}
