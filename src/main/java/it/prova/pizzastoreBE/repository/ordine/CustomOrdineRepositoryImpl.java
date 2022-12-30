package it.prova.pizzastoreBE.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastoreBE.model.Ordine;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ordine> findByExample(Ordine example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

		if (example.getData() != null) {
			whereClauses.add(" o.data  >= :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getClosed()) {
			whereClauses.add(" o.closed = :closed ");
			paramaterMap.put("closed", example.getClosed());
		}
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (example.getCliente() != null) {
			whereClauses.add(" o.cliente.id =:idCliente ");
			paramaterMap.put("idCliente", example.getCliente().getId());
		}
		if (example.getFattorino() != null) {
			whereClauses.add(" o.fattorino.id =:idFattorino ");
			paramaterMap.put("idFattorino", example.getFattorino().getId());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
