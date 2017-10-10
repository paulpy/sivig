package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Ciudad;

@Stateless
public class CiudadService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Ciudad> ciudadEventSrc;
	
	public void registrarCiudad(Ciudad ciudad)throws Exception {
		logger.info("Registrado" + ciudad.getCiudCiudad());
		em.persist(ciudad);
		ciudadEventSrc.fire(ciudad);
	}
	public void actualizarCiudad(Ciudad ciudad)throws Exception {
		logger.info("Registrado" + ciudad.getCiudCiudad());
		em.merge(ciudad);
		ciudadEventSrc.fire(ciudad);
	}
	public List<Ciudad> listCiudad(){
		List<Ciudad> ciudades = null;
		TypedQuery<Ciudad> query = em.createQuery("FROM Ciudad", Ciudad.class);
		ciudades = query.getResultList();
		return ciudades;
	}
	public Ciudad getCiudad(Integer id){
		Ciudad ciudad = null;
		ciudad = em.find(Ciudad.class, id);
		return ciudad;
	}
}
