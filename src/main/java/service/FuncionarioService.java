package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Funcionario;
import model.Persona;

@Stateless
public class FuncionarioService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Funcionario> funcionarioEventSrc;
	public void registrarFuncionario(Funcionario funcionario) throws Exception{
		logger.info("Registrado "+funcionario.getPersona().getPersNombre());
		em.persist(funcionario);
		funcionarioEventSrc.fire(funcionario);
	}
	public List<Funcionario> listFuncionario(){
		List<Funcionario> funcioanrios = null;
		TypedQuery<Funcionario> query = em.createQuery("FROM Funcionario", Funcionario.class);
		funcioanrios = query.getResultList();
		return funcioanrios;
	}
	public Funcionario getFuncionario(Integer id){
		Funcionario funcionario = null;
		funcionario = em.find(Funcionario.class, id);
		return funcionario;
	}
	public List<Funcionario> listFuncionarioTec(){
		List<Funcionario> funcioanrios = null;
		TypedQuery<Funcionario> query = em.createQuery("FROM Funcionario WHERE funcArea='tecnica'", Funcionario.class);
		funcioanrios = query.getResultList();
		return funcioanrios;
	}
	
	public void insertPersFunc(Persona persona, Funcionario funcionario){
		logger.info("Registrar "+persona.getPersNombre());
		try {
			em.persist(persona);
			funcionario.setPersona(persona);
			em.persist(funcionario);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice "+e);
		}
	}
	
	public void actualizarPersFunc(Persona persona, Funcionario funcionario){
		logger.info("Registrar "+persona.getPersNombre());
		try {
			em.merge(persona);
			funcionario.setPersona(persona);
			em.merge(funcionario);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice "+e);
		}
	}
}
