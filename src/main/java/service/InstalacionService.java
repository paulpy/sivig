package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Instalacion;

@Stateless
public class InstalacionService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Instalacion> instalacionEventSrc;
	
	public void registrarInstalacion(Instalacion instalacion){
		logger.info("Registrado" + instalacion.getInstIdInstalacion());
		em.persist(instalacion);
		instalacionEventSrc.fire(instalacion);
	}
	
	public List<Instalacion> listInstalacion(){
		List<Instalacion> instalaciones = null;
		TypedQuery<Instalacion> query = em.createQuery("FROM Instalacion", Instalacion.class);
		instalaciones = query.getResultList();
		return instalaciones;
	}
}
