package it.prova.pizzastoreBE.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastoreBE.model.Utente;
import it.prova.pizzastoreBE.service.utente.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
	
	@Autowired
	UtenteService utenteService;

	@GetMapping(value = "/rolesInfo")
	public ResponseEntity<List<String>> getRolesInfo() {

		// se sono qui significa che sono autenticato quindi devo estrarre le info dal
		// contesto
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// estraggo le info dal principal
		Utente utenteLoggato = utenteService.findByUsername(username);
		List<String> ruoli = utenteLoggato.getRuoli().stream().map(item -> item.getCodice())
				.collect(Collectors.toList());

		return ResponseEntity.ok(ruoli);
	}
}
