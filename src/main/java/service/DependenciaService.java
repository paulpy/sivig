package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Dependencia;
import model.Direccion;

@Stateless
public class DependenciaService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Dependencia> dependenciaEventSrc;
	
	public void registrarDependencia(Dependencia dependencia) throws Exception{
		logger.info("Registrado" + dependencia.getDepeNombreDependencia());
		em.persist(dependencia);
		dependenciaEventSrc.fire(dependencia);
	}
	public Boolean insertarDependencia(Dependencia dependencia, Direccion direccion){
		logger.info("Registrar"+dependencia.getDepeNombreDependencia());
		System.out.println("Registrar"+dependencia.getDepeNombreDependencia());
		try {
			em.persist(direccion);
			dependencia.setDireccion(direccion);
			em.persist(dependencia);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice "+e);
			return false;
		}
	}
	public List<Dependencia> listDependencia(){
		List<Dependencia> dependencias = null;
		TypedQuery<Dependencia> query = em.createQuery("FROM Dependencia", Dependencia.class);
		dependencias = query.getResultList();
		return dependencias;
	}
	public Dependencia getDependencia(Integer id){
		Dependencia dependencia = null;
		dependencia = em.find(Dependencia.class, id);
		return dependencia;
	}
	
	public void actualizarDependencia(Dependencia dependencia, Direccion direccion){
		logger.info("Actualizado " + dependencia.getDepeNombreDependencia());
		em.merge(direccion);
		dependencia.setDireccion(direccion);
		em.merge(dependencia);
		dependenciaEventSrc.fire(dependencia);
	}
}
