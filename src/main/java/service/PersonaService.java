package service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Funcionario;
import model.Persona;
import model.Usuario;


@Stateless
public class PersonaService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Persona> personaEventSrc;
	public void registrarPersona(Persona persona) throws Exception{
		logger.info("Registrar "+persona.getPersNombre());
		em.persist(persona);
		personaEventSrc.fire(persona);
	}
	public Persona getPersonaFind(Integer personaCi){
		Persona persona = null;
		Query query = em.createQuery("FORM Persona WHERE persCi = "+personaCi);
		persona = (Persona) query.getSingleResult();
		return persona;
	}
	public Boolean inserPersFuncUsua(Persona persona, Funcionario funcionario, Usuario usuario){
		logger.info("Registrar "+persona.getPersNombre());
		try {
			em.persist(persona);
			funcionario.setPersona(persona);
			em.persist(funcionario);
			usuario.setFuncionario(funcionario);
			em.persist(usuario);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice"+e);
			return false;
		}
	}
	public Boolean insertPersFunc(Persona persona, Funcionario funcionario){
		logger.info("Registrar "+persona.getPersNombre());
		try {
			em.persist(persona);
			funcionario.setPersona(persona);
			em.persist(funcionario);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice"+e);
			return false;
		}
	}
	
	public Persona getPersona(Integer id){
		Persona persona;
		persona = em.find(Persona.class, id);
		return persona;
	}
}
