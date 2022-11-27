package it.prova.gestionetratte.repository.tratta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionetratte.model.Tratta;

public class CustomTrattaRepositoryImpl implements CustomTrattaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Tratta> findByExample(Tratta example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select t from Tratta t where t.id = t.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" t.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" t.descrizione like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getData() != null) {
			whereClauses.add(" t.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getOraDecollo() != null) {
			whereClauses.add("t.oraDecollo >= :oraDecollo ");
			paramaterMap.put("oraDecollo", example.getOraDecollo());
		}
		if (example.getOraAtterraggio() != null) {
			whereClauses.add(" t.oraAtterraggio >= :oraAtterraggio ");
			paramaterMap.put("oraAtterraggio", example.getOraAtterraggio());
		}
		if (example.getStato() != null) {
			whereClauses.add(" t.stato >= :stato ");
			paramaterMap.put("stato", example.getStato());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tratta> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tratta.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
