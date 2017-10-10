package service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Direccion;


@Stateless
public class DireccionService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Direccion> direccionEventSrc;
	
	public void registrarDireccion(Direccion direccion) throws Exception{
		logger.info("Registrado" + direccion.getCalle1().getCallCalle());
		em.persist(direccion);
		direccionEventSrc.fire(direccion);
	}
	
	public Direccion getDireccion(Integer id){
		Direccion direccion;
		direccion = em.find(Direccion.class, id);
		return direccion;
	}
	
	public void actualizarDireccion(Direccion direccion) throws Exception{
		logger.info("Registrado" + direccion.getCalle1().getCallCalle());
		em.merge(direccion);
		direccionEventSrc.fire(direccion);
	}
}
