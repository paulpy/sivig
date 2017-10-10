package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.CausaCambioEstado;

@Stateless
public class CambioEstadoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<CausaCambioEstado> causaCambioEstadoEventSrc;
	
	public void registrarCambioEstado(CausaCambioEstado cambioEstado) throws Exception {
		logger.info("Registrando" + cambioEstado.getCcesCausaEstado());
		em.persist(cambioEstado);
		causaCambioEstadoEventSrc.fire(cambioEstado);
	}
	
	public List<CausaCambioEstado> listCausaCambioEstado(){
		List<CausaCambioEstado> cambioEstados = null;
		TypedQuery<CausaCambioEstado> query = em.createQuery("FROM CausaCambioEstado", CausaCambioEstado.class);
		cambioEstados = query.getResultList();
		return cambioEstados;
	}
	
	public void actualizarCambioEstado(CausaCambioEstado cambioEstado) throws Exception {
		logger.info("Registrando" + cambioEstado.getCcesCausaEstado());
		em.merge(cambioEstado);
		causaCambioEstadoEventSrc.fire(cambioEstado);
	}
	
	public CausaCambioEstado getCambioEstado(Integer id){
		CausaCambioEstado cambioEstado = null;
		cambioEstado = em.find(CausaCambioEstado.class, id);
		return cambioEstado;
	}
	
	public List<String> listCambioEstadoNom(){
		List<String> cambioEstado = null;
		TypedQuery<String> query = em.createQuery("SELECT cce.ccesCausaEstado FROM CausaCambioEstado AS cce", String.class);
		cambioEstado = query.getResultList();
		return cambioEstado;
	}
	
	public CausaCambioEstado getCausaCambioEstado(String causaEstado){
		CausaCambioEstado causaCambio = null;
		TypedQuery<CausaCambioEstado> query = em.createQuery("FROM CausaCambioEstado AS cce WHERE cce.ccesCausaEstado = :ccesCausaEstado", CausaCambioEstado.class);
		causaCambio = query.setParameter("ccesCausaEstado", causaEstado).getSingleResult();
		return causaCambio;
	}
}
