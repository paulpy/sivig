package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.solder.servlet.http.RequestParam;

import model.Usuario;
import service.UsuarioService;

@ViewScoped
@ManagedBean
public class UsuarioMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario nuevoUsuario;
	@Inject
	private FacesContext context;
	@Inject
	private UsuarioService usuarioService;
	@RequestParam
	private String idUsuario;
	private ExternalContext externalContext;
	private List<Usuario> usuarioList;
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if(idUsuario != null){
				limpiar();
				Integer id = Integer.parseInt(idUsuario);
				nuevoUsuario = usuarioService.getUsuario(id);
			}
		} else {
			limpiar();
			listarUsuarios();
		}
	}
	public void guardarUsuario(){
		try {
			if(idUsuario != null){
				usuarioService.actualizarUsuario(nuevoUsuario);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/listadoacciones.xhtml");
				context.addMessage(null, m);
			} else {
				usuarioService.registrarUsuario(nuevoUsuario);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				inicializar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public String estadoUsuario(boolean estadoUsu){
		String usuarioEstadoEnv = null;
		if(estadoUsu){
			usuarioEstadoEnv = "Activo";
		} else {
			usuarioEstadoEnv = "Inactivo";
		}
		return usuarioEstadoEnv;
	}
	
	public String colorEstadoUsuario(boolean estadoUsu){
		String colorestado = null;
		if(estadoUsu){
			colorestado = "green";
		} else {
			colorestado = "red";
		}
		return colorestado;
	}
	
	public void listarUsuarios(){
		usuarioList = usuarioService.listUsuario();
	}
	public void limpiar(){
		nuevoUsuario = new Usuario();
	}
	public Usuario getNuevoUsuario() {
		return nuevoUsuario;
	}
	public void setNuevoUsuario(Usuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}
	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
	
}
