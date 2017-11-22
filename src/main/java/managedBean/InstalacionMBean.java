package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.solder.servlet.http.RequestParam;

import clases.AuditoriaClass;
import model.Dependencia;
import model.Equipo;
import model.Funcionario;
import model.Instalacion;
import service.DependenciaService;
import service.EquipoService;
import service.FuncionarioService;
import service.InstalacionService;

@ViewScoped
@ManagedBean
public class InstalacionMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Instalacion nuevaInstalacion;
	
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
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idInstalacionParam;

	private List<Equipo> equiposList;
	private List<Funcionario> funcionariosList;
	private List<Dependencia> dependenciaList;
	private List<Instalacion> instalaccionList;
	
	public void inicializar(){
		limpiar();
		listarEFD();
	}
	
	public void registrarInstalacion(String usuario)throws Exception{
		try{
			instalacionService.registrarInstalacion(nuevaInstalacion);
			auditoriaClass.agregarAuditoria("Agregado Instalacion "+nuevaInstalacion.getDependencia().getDepeNombreDependencia()+"-"+nuevaInstalacion.getEquipo().getEquiIdenificador(), "Instalacion", usuario);
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
		nuevaInstalacion = new Instalacion();
	}
	
	public void listarInstalaciones(){
		instalaccionList = instalacionService.listInstalacion();
	}
	
	public void listarEFD(){
		funcionariosList = funcionarioService.listFuncionario();
		dependenciaList = dependenciaService.listDependencia();
		equiposList = equipoService.listEquipo();
	}

	public Instalacion getNuevaInstalacion() {
		return nuevaInstalacion;
	}

	public void setNuevaInstalacion(Instalacion nuevaInstalacion) {
		this.nuevaInstalacion = nuevaInstalacion;
	}

	public String getIdInstalacionParam() {
		return idInstalacionParam;
	}

	public void setIdInstalacionParam(String idInstalacionParam) {
		this.idInstalacionParam = idInstalacionParam;
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

	public List<Dependencia> getDependenciaList() {
		return dependenciaList;
	}

	public void setDependenciaList(List<Dependencia> dependenciaList) {
		this.dependenciaList = dependenciaList;
	}

	public List<Instalacion> getInstalaccionList() {
		return instalaccionList;
	}

	public void setInstalaccionList(List<Instalacion> instalaccionList) {
		this.instalaccionList = instalaccionList;
	}
	
	
}
