package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Accion;
@Stateless
public class AccionService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Accion> accionEventSrc;
	
	public void registrarAccion(Accion accion) throws Exception{
		logger.info("Registrando "+ accion.getAcciAccion());
		em.persist(accion);
		accionEventSrc.fire(accion);
	}
	
	public void actualizaAccion(Accion accion) throws Exception{
		logger.info("Registrando "+ accion.getAcciAccion());
		em.merge(accion);
		accionEventSrc.fire(accion);
	}
	
	public List<Accion> listAccion(){
		List<Accion> acciones = null;
		TypedQuery<Accion> query = em.createQuery("FROM Accion", Accion.class);
		acciones = query.getResultList();
		return acciones;
	}
	
	public Accion getAccion(Integer id){
		Accion accion = null;
		accion = em.find(Accion.class, id);
		return accion;	
	}
	
	public List<String> listAccionNom(){
		List<String> accion = null;
		TypedQuery<String> query = em.createQuery("SELECT a.acciAccion FROM Accion AS a", String.class);
		accion = query.getResultList();
		return accion;
	}
}
