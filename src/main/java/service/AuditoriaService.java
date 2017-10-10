package service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Auditoria;

@Stateless
public class AuditoriaService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Auditoria> auditoriaEventSrc;
	
	public void registrarAuditoria(Auditoria auditoria) throws Exception {
		logger.info("Registrando" + auditoria.getAudiDetalleAccion());
		em.persist(auditoria);
		auditoriaEventSrc.fire(auditoria);
	}
}
