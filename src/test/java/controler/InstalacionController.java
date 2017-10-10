package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Dependencia;
import model.Equipo;
import model.Funcionario;
import model.Instalacion;
import service.DependenciaService;
import service.EquipoService;
import service.FuncionarioService;
import service.InstalacionService;

@Model
public class InstalacionController {
	@Inject
	private FacesContext context;
	@Inject
	private InstalacionService instalacionService;
	@Inject
	private EquipoService equipoService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private DependenciaService dependenciaService;
	@Produces
	@Named
	private Instalacion nuevaInstalacion;
	private List<Equipo> equiposList;
	private List<Funcionario> funcionariosList;
	private List<Dependencia> dependenciaList;
	private List<Instalacion> instalaccionList;
	public List<Instalacion> getInstalaccionList() {
		return instalaccionList;
	}
	public void setInstalaccionList(List<Instalacion> instalaccionList) {
		this.instalaccionList = instalaccionList;
	}
	public List<Dependencia> getDependenciaList() {
		return dependenciaList;
	}
	public void setDependenciaList(List<Dependencia> dependenciaList) {
		this.dependenciaList = dependenciaList;
	}
	public List<Equipo> getEquiposList() {
		return equiposList;
	}
	public void setEquiposList(List<Equipo> equiposList) {
		this.equiposList = equiposList;
	}
	public List<Funcionario> getFuncionariosList() {
		return funcionariosList;
	}
	public void setFuncionariosList(List<Funcionario> funcionariosList) {
		this.funcionariosList = funcionariosList;
	}
	@PostConstruct
	public void initNuevaInstalacion(){
		nuevaInstalacion = new Instalacion();
		equiposList = equipoService.listEquipo();
		funcionariosList = funcionarioService.listFuncionario();
		dependenciaList = dependenciaService.listDependencia();
		instalaccionList = instalacionService.listInstalacion();
	}
	public void registrarInstalacion() throws Exception {
		try {
			instalacionService.registrarInstalacion(nuevaInstalacion);
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
