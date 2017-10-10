package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Pieza;

@Stateless
public class PiezaService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Pieza> piezaEventSrc;
	public void registrarPieza(Pieza pieza) throws Exception{
		logger.info("Registrar "+pieza.getPiezDetalle());
		em.persist(pieza);
		piezaEventSrc.fire(pieza);
	}
	public List<Pieza> listPieza(){
		List<Pieza> piezas = null;
		TypedQuery<Pieza> query = em.createQuery("FROM Pieza", Pieza.class);
		piezas = query.getResultList();
		return piezas;
	}
	public Pieza getPieza(Integer id){
		Pieza pieza = null;
		pieza = em.find(Pieza.class, id);
		return pieza;
	}
	
	public void actualizarPieza(Pieza pieza) throws Exception{
		logger.info("Registrar "+pieza.getPiezDetalle());
		em.merge(pieza);
		piezaEventSrc.fire(pieza);
	}
}
