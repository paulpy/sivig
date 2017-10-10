package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.solder.servlet.http.RequestParam;

import model.Contrato;
import model.Dependencia;
import model.Funcionario;
import model.Mantenimiento;
import service.ContratoService;
import service.DependenciaService;
import service.FuncionarioService;
import service.MantenimientoService;

@ViewScoped
@ManagedBean
public class MantenimientoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mantenimiento nuevoMantenimiento;
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
	@RequestParam
	private String idMantenimientoParam;

	private List<Dependencia> dependenciaList;
	private List<Funcionario> funcionarioList;
	private List<Contrato> contratoList;
	
	public void inicializar(){
		limpiar();
		listarDFC();
	}
	
	public void registrarMantenimiento() throws Exception {
		try {
			mantenimientoService.registrarMantenimiento(nuevoMantenimiento);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
	
	public void limpiar(){
		nuevoMantenimiento = new Mantenimiento();
	}
	
	public void listarDFC(){
		dependenciaList = dependenciaService.listDependencia();
		funcionarioList = funcionarioService.listFuncionario();
		contratoList = contratoService.listContrato();
	}

	public Mantenimiento getNuevoMantenimiento() {
		return nuevoMantenimiento;
	}

	public void setNuevoMantenimiento(Mantenimiento nuevoMantenimiento) {
		this.nuevoMantenimiento = nuevoMantenimiento;
	}

	public String getIdMantenimientoParam() {
		return idMantenimientoParam;
	}

	public void setIdMantenimientoParam(String idMantenimientoParam) {
		this.idMantenimientoParam = idMantenimientoParam;
	}

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
	
	
}
