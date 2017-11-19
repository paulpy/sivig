package clases;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.inject.Inject;

import model.Auditoria;
import model.Usuario;
import service.AuditoriaService;
import service.UsuarioService;

public class AuditoriaClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuarioRecuperado;
	private Auditoria auditoria;
	
	@Inject
	private AuditoriaService auditoriaService;
	
	@Inject
	private UsuarioService usuarioService;
	
	public void agregarAuditoria(String accion, String tabla, String usuario){
		limpiar();
		usuarioRecuperado = usuarioService.getUsuario(usuario);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		auditoria.setAudiDetalleAccion(accion);
		auditoria.setAudiMomentoAccion(timestamp);
		auditoria.setAudiTabla(tabla);
		auditoria.setUsuario(usuarioRecuperado);
		try {
			auditoriaService.registrarAuditoria(auditoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.toString();
		}
		limpiar();
	}
	
	public void limpiar(){
		usuarioRecuperado = new Usuario();
		auditoria = new Auditoria();
	}
	
	
}
