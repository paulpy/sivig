package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Contrato;

@Stateless
public class ContratoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Contrato> contratoEventSrc;
	public void registrarContrato(Contrato contrato) throws Exception {
		logger.info("Registrado " + contrato.getContObservacion());
		em.persist(contrato);
		contratoEventSrc.fire(contrato);
	}
	public List<Contrato> listContrato(){
		List<Contrato> contratos = null;
		TypedQuery<Contrato> query = em.createQuery("FROM Contrato", Contrato.class);
		contratos = query.getResultList();
		return contratos;
	}
	public Contrato getContrato(Integer id){
		Contrato contrato = null;
		contrato = em.find(Contrato.class, id);
		return contrato;
	}
	public void actualizarContrato(Contrato contrato) throws Exception {
		logger.info("Registrado " + contrato.getContObservacion());
		em.merge(contrato);
		contratoEventSrc.fire(contrato);
	}
	
}
