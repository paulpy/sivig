package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Calle;
@Stateless
public class CalleService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Calle> calleEventSrc;
	
	public void registrarCalle(Calle calle) throws Exception {
		logger.info("Registrando" + calle.getCallCalle());
		em.persist(calle);
		calleEventSrc.fire(calle);
	}
	
	public void actualizarCalle(Calle calle) throws Exception {
		logger.info("Registrando" + calle.getCallCalle());
		em.merge(calle);
		calleEventSrc.fire(calle);
	}
	
	public List<Calle> listCalle(){
		List<Calle> calles = null;
		TypedQuery<Calle> query = em.createQuery("FROM Calle", Calle.class);
		calles = query.getResultList();
		return calles;
	}
	public Calle getCalle(Integer id){
		Calle calle = null;
		calle = em.find(Calle.class, id);
		return calle;
	}
}
