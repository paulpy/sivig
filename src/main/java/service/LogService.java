package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Stateless
public class LogService {
	@Inject
	private EntityManager em;
	public List<String> mostrarLogEquipos(){
		List<String> logEquipos = null;
		TypedQuery<String> query = em.createQuery("SELECT heq.eqesIdHistoricoEstadoReloj, e.equiIdentificador, ee.eseqEstadoEquipo FROM HistoricoEquiposEstados AS heq, Equipos AS e, "
				+ "EstadosEquipos AS ee  WHERE heq.eqesIdEquipo = e.equiIdEquipo AND heq.eqesIdEstadoEquipo = ee.eseqIdEstadoEquipo ORDER BY heq.eqesIdHistoricoEstadoReloj ASC", String.class);
		logEquipos = query.getResultList();
		return logEquipos;
	}
	
}
 