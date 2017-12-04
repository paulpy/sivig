package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Equipo;

@Stateless
public class EquipoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Equipo> equipoEventSrc;
	public void registrarEquipo(Equipo equipo) throws Exception{
		logger.info("Registrado "+equipo.getEquiSerieEquipo());
		em.persist(equipo);
		equipoEventSrc.fire(equipo);
	}
	public void updateEquipo(Equipo equipo) throws Exception{
		logger.info("Actualizado"+equipo.getEquiSerieEquipo());
		em.merge(equipo);
		equipoEventSrc.fire(equipo);
	}
	public List<Equipo> listEquipo(){
		List<Equipo> equipos = null;
		TypedQuery<Equipo> query = em.createQuery("FROM Equipo", Equipo.class);
		equipos = query.getResultList();
		return equipos;
	}
	public List<Equipo> listEquipoActivos(){
		List<Equipo> equipos = null;
		TypedQuery<Equipo> query = em.createQuery("FROM Equipo AS e WHERE e.raspberry.raspActivo = true", Equipo.class);
		equipos = query.getResultList();
		return equipos;
	}
	public Equipo getEquipo(Integer id){
		Equipo equipo = null;
		equipo = em.find(Equipo.class, id);
		return equipo;
	}
	
	public Equipo getEquipoIdentidicador(String identificadorEquipo){
		Equipo equipo = null;
		TypedQuery<Equipo> query = em.createQuery("FROM Equipo AS e WHERE e.equiIdentificador = :equiIdentificador", Equipo.class);
		equipo = query.setParameter("equiIdentificador", identificadorEquipo).getSingleResult();
		return equipo;
	}
	
	public Boolean getExisteEquipo(String identificadorEquipo){
		Equipo equipo = null;
		Boolean existe = false;
		TypedQuery<Equipo> query = em.createQuery("FROM Equipo AS e WHERE e.equiIdentificador = :equiIdentificador", Equipo.class);
		try {
			equipo = query.setParameter("equiIdentificador", identificadorEquipo).getSingleResult();
			existe = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
			existe = false;
		}
		return existe;
	}
}
