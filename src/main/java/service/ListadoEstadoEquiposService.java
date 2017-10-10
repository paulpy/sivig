package service;

import java.sql.ResultSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class ListadoEstadoEquiposService {
	@Inject
	private EntityManager em;
	
	public ResultSet vistaestadocojunto(){
		ResultSet estadoconjunto = null;
		Query query = em.createQuery("select * from ");
		estadoconjunto = (ResultSet) query.getResultList();
		return estadoconjunto;
	}
}
