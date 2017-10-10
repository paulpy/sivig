package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Funcionario;
import model.Persona;
import service.FuncionarioService;
import service.PersonaService;

@Model
public class FuncionarioController {
	@Inject
	private FacesContext context;
	@Inject
	private PersonaService personaService;
	@Inject
	private FuncionarioService funcionarioService;
	@Produces
	@Named
	private Funcionario nuevoFuncionario;
	@Produces
	@Named
	private Persona nuevoFuncPersona;
	private List<Funcionario> funcionarioList;
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	@PostConstruct
	public void initNuevoFuncionario(){
		nuevoFuncionario = new Funcionario();
		nuevoFuncPersona = new Persona();
		funcionarioList = funcionarioService.listFuncionario();
	}
	public void registrarFuncionario() throws Exception {
		try {
			if(personaService.insertPersFunc(nuevoFuncPersona, nuevoFuncionario)){
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
			} else {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Fallo el Registro");
				context.addMessage(null, m);
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
}
