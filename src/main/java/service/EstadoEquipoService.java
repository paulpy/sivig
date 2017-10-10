package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.EstadoEquipo;

@Stateless
public class EstadoEquipoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<EstadoEquipo> estadoEquipoEventSrc;
	
	public void registrarEstadoEquipo(EstadoEquipo estadoEquipo) throws Exception{
		logger.info("Registrando" + estadoEquipo.getEseqEstadoEquipo());
		em.persist(estadoEquipo);
		estadoEquipoEventSrc.fire(estadoEquipo);
	}
	public void actualizarEstadoEquipo(EstadoEquipo estadoEquipo) throws Exception{
		logger.info("Registrando" + estadoEquipo.getEseqEstadoEquipo());
		em.merge(estadoEquipo);
		estadoEquipoEventSrc.fire(estadoEquipo);
	}
	public List<EstadoEquipo> listEstadoEquipos(){
		List<EstadoEquipo> estadoEquipos = null;
		TypedQuery<EstadoEquipo> query = em.createQuery("FROM EstadoEquipo", EstadoEquipo.class);
		estadoEquipos = query.getResultList();
		return estadoEquipos;
	}
	public EstadoEquipo getEstadoEquipo(Integer id){
		EstadoEquipo estadoEquipo = null;
		estadoEquipo = em.find(EstadoEquipo.class, id);
		return estadoEquipo;
	}
	public List<String> listEstadoEquiposNom(){
		List<String> estadoEquipos = null;
		TypedQuery<String> query = em.createQuery("SELECT ee.eseqEstadoEquipo FROM EstadoEquipo AS ee", String.class);
		estadoEquipos = query.getResultList();
		return estadoEquipos;
	}
	
	public EstadoEquipo getEstadoEquipo(String estadoEqu){
		EstadoEquipo estadoEquipo = null;
		TypedQuery<EstadoEquipo> query = em.createQuery("FROM EstadoEquipo AS ee WHERE ee.eseqEstadoEquipo = :eseqEstadoEquipo", EstadoEquipo.class);
		estadoEquipo = query.setParameter("eseqEstadoEquipo", estadoEqu).getSingleResult();
		return estadoEquipo;
	}
}
