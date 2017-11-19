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
import model.Calle;
import model.Ciudad;
import model.Dependencia;
import model.Direccion;
import model.Entidad;
import service.CalleService;
import service.CiudadService;
import service.DependenciaService;
import service.DireccionService;
import service.EntidadService;

@ViewScoped
@ManagedBean
public class DependenciaMBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dependencia nuevaDependencia;
	private Direccion nuevaDireccion;
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
	@Inject
	private DireccionService direccionService;
	@Inject
	private AuditoriaClass auditoriaClass;
	
	@RequestParam
	private String idDependencia;
	
	private ExternalContext externalContext;
	
	private List<Entidad> entidadesList;
	private List<Calle> calleList;
	private List<Ciudad> ciudadList;
	private List<Dependencia> dependenciasList;

	public void inicializar() throws Exception {
		if(!FacesContext.getCurrentInstance().isPostback()){
			if(idDependencia != null){
				limpiar();
				listarCalleCiudadEntidad();
				Integer iddepe = Integer.parseInt(idDependencia);
				nuevaDependencia = dependenciaService.getDependencia(iddepe);
				Integer iddire = nuevaDependencia.getDireccion().getDireIdDireccion();
				nuevaDireccion = direccionService.getDireccion(iddire);
				} else {
					limpiar();
					listarCalleCiudadEntidad();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarDependencia(String usuariodata){
		try {
			if (idDependencia != null) {
				dependenciaService.actualizarDependencia(nuevaDependencia, nuevaDireccion);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando Dependencia", "Dependencia", usuariodata);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/empresarial/clientes/estadodependencias.xhtml");
			} else {
				dependenciaService.insertarDependencia(nuevaDependencia, nuevaDireccion);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando Dependencia", "Dependencia", usuariodata);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		} finally {
			limpiar();
		}
	}
	
	public void listarCalleCiudadEntidad(){
		calleList = calleService.listCalle();
		ciudadList = ciudadService.listCiudad();
		entidadesList = entidadService.listEntidad();
	}
	
	public void limpiar(){
		nuevaDependencia = new Dependencia();
		nuevaDireccion = new Direccion();
	}
	
	public String estadoDependenciaView(boolean estadoDependencia){
		String estadoDep = null;
		if(estadoDependencia){
			estadoDep = "Activo";
		} else {
			estadoDep = "Inactivo";
		}
		return estadoDep;
	}
	
	public void listarDependencias(){
		dependenciasList = dependenciaService.listDependencia();
	}

	public Dependencia getNuevaDependencia() {
		return nuevaDependencia;
	}

	public void setNuevaDependencia(Dependencia nuevaDependencia) {
		this.nuevaDependencia = nuevaDependencia;
	}

	public String getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(String idDependencia) {
		this.idDependencia = idDependencia;
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

	public List<Dependencia> getDependenciasList() {
		return dependenciasList;
	}

	public void setDependenciasList(List<Dependencia> dependenciasList) {
		this.dependenciasList = dependenciasList;
	}

	public Direccion getNuevaDireccion() {
		return nuevaDireccion;
	}

	public void setNuevaDireccion(Direccion nuevaDireccion) {
		this.nuevaDireccion = nuevaDireccion;
	}

}
