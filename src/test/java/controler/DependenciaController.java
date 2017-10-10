package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Calle;
import model.Ciudad;
import model.Dependencia;
import model.Direccion;
import model.Entidad;
import service.CalleService;
import service.CiudadService;
import service.DependenciaService;
import service.EntidadService;

@Model
public class DependenciaController {
	@Inject
	private FacesContext context;
	@Inject
	private DependenciaService dependenciaService;
	@Inject
	private EntidadService entidadService;
	@Inject
	private CalleService calleService;
	@Inject
	private CiudadService ciudadService;
	@Produces
	@Named
	private Dependencia nuevaDependencia;
	@Produces
	@Named
	private Direccion nuevaDepeDireccion;
	private List<Entidad> entidadesList;
	private List<Calle> calleList;
	private List<Ciudad> ciudadList;
	private List<Dependencia> dependenciasList;
	public List<Dependencia> getDependenciasList() {
		return dependenciasList;
	}
	public void setDependenciasList(List<Dependencia> dependenciasList) {
		this.dependenciasList = dependenciasList;
	}
	public List<Entidad> getEntidadesList() {
		return entidadesList;
	}
	public void setEntidadesList(List<Entidad> entidadesList) {
		this.entidadesList = entidadesList;
	}
	public List<Calle> getCalleList() {
		return calleList;
	}
	public void setCalleList(List<Calle> calleList) {
		this.calleList = calleList;
	}
	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}
	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}
	@PostConstruct
	public void initNuevaDependencia(){
		nuevaDependencia = new Dependencia();
		entidadesList = entidadService.listEntidad();
		calleList = calleService.listCalle();
		ciudadList = ciudadService.listCiudad();
		dependenciasList = dependenciaService.listDependencia();
		nuevaDepeDireccion = new Direccion();	
	}
	public void registrarDependencia() throws Exception{
		try {
			dependenciaService.insertarDependencia( nuevaDependencia, nuevaDepeDireccion);
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
