package service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import model.Mantenimiento;

@Stateless
public class MantenimientoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Mantenimiento> mantenimientoEventSrc;
	public void registrarMantenimiento(Mantenimiento mantenimiento) throws Exception{
		logger.info("Registrado "+mantenimiento.getMantObservacion());
		em.persist(mantenimiento);
		mantenimientoEventSrc.fire(mantenimiento);
	}
}
