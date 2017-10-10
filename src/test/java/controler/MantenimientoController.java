package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Contrato;
import model.Dependencia;
import model.Funcionario;
import model.Mantenimiento;
import service.ContratoService;
import service.DependenciaService;
import service.FuncionarioService;
import service.MantenimientoService;

@Model
public class MantenimientoController {
	@Inject
	private FacesContext context;
	@Inject
	private MantenimientoService mantenimientoService;
	@Inject
	private DependenciaService dependenciaService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private ContratoService contratoService;
	@Produces
	@Named
	private Mantenimiento nuevoMantenimiento;
	private List<Dependencia> dependenciaList;
	private List<Funcionario> funcionarioList;
	private List<Contrato> contratoList;
	public List<Dependencia> getDependenciaList() {
		return dependenciaList;
	}
	public void setDependenciaList(List<Dependencia> dependenciaList) {
		this.dependenciaList = dependenciaList;
	}
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	public List<Contrato> getContratoList() {
		return contratoList;
	}
	public void setContratoList(List<Contrato> contratoList) {
		this.contratoList = contratoList;
	}
	@PostConstruct
	public void initNuevaMantenimiento(){
		nuevoMantenimiento = new Mantenimiento();
		dependenciaList = dependenciaService.listDependencia();
		funcionarioList = funcionarioService.listFuncionario();
		contratoList = contratoService.listContrato();
	}
	public void registrarMantenimiento() throws Exception {
		try {
			mantenimientoService.registrarMantenimiento(nuevoMantenimiento);;
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
}
