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

import clases.AuditoriaClass;
import model.Funcionario;
import model.Persona;
import service.FuncionarioService;
import service.PersonaService;

@ViewScoped
@ManagedBean
public class FuncionarioMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario nuevoFuncionario;
	private Persona nuevoFuncPersona;
	@Inject
	private FacesContext context;
	@Inject
	private PersonaService personaService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private AuditoriaClass auditoriaClass;
	
	@RequestParam
	private String idFuncionarioParam;

	private ExternalContext externalContext;
	
	private List<Funcionario> funcionarioList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idFuncionarioParam != null){
				limpiar();
				Integer idfun = Integer.parseInt(idFuncionarioParam);
				nuevoFuncionario = funcionarioService.getFuncionario(idfun);
				Integer idper = nuevoFuncionario.getPersona().getPersIdPersona();
				nuevoFuncPersona = personaService.getPersona(idper);
			} else {
				limpiar();
				listarFuncionario();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarFuncionario(String usuariodata){
		try {
			if(idFuncionarioParam != null){
				funcionarioService.actualizarPersFunc(nuevoFuncPersona, nuevoFuncionario);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando Funcionario "+nuevoFuncionario.getPersona().getPersIdPersona(), "Funcionario", usuariodata);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/empresarial/funcionarios/estadofuncionario.xhtml");
			} else {
				funcionarioService.insertPersFunc(nuevoFuncPersona, nuevoFuncionario);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando Funcionario "+nuevoFuncionario.getPersona().getPersIdPersona(), "Funcionario", usuariodata);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public void limpiar(){
		nuevoFuncionario = new Funcionario();
		nuevoFuncPersona = new Persona();
	}
	
	public void listarFuncionario(){
		funcionarioList = funcionarioService.listFuncionario();
	}
	
	public String estadoFuncionarioView(boolean estadoFuncionarioActual){
		String estadoFunc = null;
		if(estadoFuncionarioActual){
			estadoFunc = "Activo";
		} else {
			estadoFunc = "Inactivo";
		}
		return estadoFunc;
	}

	public Funcionario getNuevoFuncionario() {
		return nuevoFuncionario;
	}

	public void setNuevoFuncionario(Funcionario nuevoFuncionario) {
		this.nuevoFuncionario = nuevoFuncionario;
	}

	public Persona getNuevoFuncPersona() {
		return nuevoFuncPersona;
	}

	public void setNuevoFuncPersona(Persona nuevoFuncPersona) {
		this.nuevoFuncPersona = nuevoFuncPersona;
	}

	public String getIdFuncionarioParam() {
		return idFuncionarioParam;
	}

	public void setIdFuncionarioParam(String idFuncionarioParam) {
		this.idFuncionarioParam = idFuncionarioParam;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	
}
