package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Usuario;

@Stateless
public class UsuarioService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Usuario> usuarioEventSrc;
	public void registrarUsuario(Usuario usuario){
		logger.info("Registrado "+ usuario.getUsuaUsuario());
		em.persist(usuario);
		usuarioEventSrc.fire(usuario);
	}
	
	public void actualizarUsuario(Usuario usuario){
		logger.info("Registrado "+ usuario.getUsuaUsuario());
		em.merge(usuario);
		usuarioEventSrc.fire(usuario);
	}
	
	public Usuario getUsuario(String username){
		Usuario usuario = null;
		TypedQuery<Usuario> query = em.createQuery("FROM Usuario WHERE usuaUsuario='"+username+"'", Usuario.class);
		try {
			usuario = query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Usuario no encontrado retorna null");
			usuario = null;
		}
		return usuario;
	}
	
	public List<Usuario> listUsuario(){
		List<Usuario> usuarios = null;
		TypedQuery<Usuario> query = em.createQuery("FROM Usuario", Usuario.class);
		usuarios = query.getResultList();
		return usuarios;
	}
	
	public Usuario getUsuario(Integer id){
		Usuario usuario = null;
		usuario = em.find(Usuario.class, id);
		return usuario;
	}
}
