package service;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.CambioPieza;
@Stateless
public class CambioPiezaService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<CambioPieza> cambioPiezaEventSrc;
	public void registrarCambioPieza(CambioPieza cambioPieza){
		logger.info("Registrado" + cambioPieza.getCapiObservacion());
		em.persist(cambioPieza);
		cambioPiezaEventSrc.fire(cambioPieza);
	}
}
