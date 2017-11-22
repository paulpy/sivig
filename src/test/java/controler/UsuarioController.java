package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import clases.AuditoriaClass;
import model.Funcionario;
import model.Persona;
import model.Usuario;
import service.PersonaService;
import service.UsuarioService;


@Model
public class UsuarioController {
	@Inject
	private FacesContext context;
	@Inject
	private PersonaService personaService;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@Produces
	@Named
	private Usuario nuevoUsuario;
	@Produces
	@Named
	private Funcionario nuevoUsuaFuncionario;
	private List<Usuario> usuariosList;
	@Produces
	@Named
	private Persona nuevoUsuaPersona;
	public List<Usuario> getUsuariosList() {
		return usuariosList;
	}
	public void setUsuariosList(List<Usuario> usuariosList) {
		this.usuariosList = usuariosList;
	}
	@PostConstruct
	public void initNuevoUsuario(){
		nuevoUsuario = new Usuario();
		nuevoUsuaFuncionario = new Funcionario();
		nuevoUsuaPersona = new Persona();
		usuariosList = usuarioService.listUsuario();
	}
	public void registrarUsuario(String usuario) throws Exception {
		try {
			if(personaService.inserPersFuncUsua(nuevoUsuaPersona, nuevoUsuaFuncionario, nuevoUsuario)){
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando usuario "+nuevoUsuario.getUsuaUsuario(), "usuario", usuario);
			} else {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Registro no guardado");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando usuario "+nuevoUsuario.getUsuaUsuario(), "usuario", usuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
}
