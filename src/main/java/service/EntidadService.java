package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Entidad;

@Stateless
public class EntidadService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Entidad> entidadEventSrc;
	public void registrarEntidad(Entidad entidad) throws Exception{
		logger.info("Registrado"+entidad.getEntiEntidad());
		em.persist(entidad);
		entidadEventSrc.fire(entidad);
	}
	public List<Entidad> listEntidad(){
		List<Entidad> entidades = null;
		TypedQuery<Entidad> query = em.createQuery("FROM Entidad",Entidad.class);
		entidades = query.getResultList();
		return entidades;
	}
	public Entidad getEntidad(Integer id){
		Entidad entidad = null;
		entidad = em.find(Entidad.class, id);
		return entidad;
	}
	public void actualizarEntidad(Entidad entidad) throws Exception {
		logger.info("Registrado " + entidad.getEntiEntidad());
		em.merge(entidad);
		entidadEventSrc.fire(entidad);
	}
}
