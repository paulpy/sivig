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
import model.Entidad;
import service.EntidadService;

@ViewScoped
@ManagedBean
public class EntidadMbean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Entidad nuevaEntidad;
	@Inject
	private FacesContext context;
	@Inject
	private EntidadService entidadService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idEntidadParam;
	
	private ExternalContext externalContext;
	
	private List<Entidad> entidadList;
	
	public void inicializar() throws Exception {
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idEntidadParam != null) {
				nuevaEntidad = new Entidad();
				listarEntidades();
				Integer id = Integer.parseInt(idEntidadParam);
				nuevaEntidad = entidadService.getEntidad(id);
			} else {
				limpiar();
				listarEntidades();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();	
	}
	
	public void guardarEntidad(String usuariodata){
		try {
			if(idEntidadParam != null){
				entidadService.actualizarEntidad(nuevaEntidad);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando Entidad "+nuevaEntidad.getEntiEntidad(), "Entidad", usuariodata);auditoriaClass.agregarAuditoria("Actualizando", "Accion", usuariodata);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/empresarial/clientes/estadoentidad.xhtml");
			} else {
				entidadService.registrarEntidad(nuevaEntidad);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando Entidad "+nuevaEntidad.getEntiEntidad(), "Entidad", usuariodata);
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
	
	public void listarEntidades(){
		entidadList = entidadService.listEntidad();
	}
	
	public void limpiar(){
		nuevaEntidad = new Entidad();
	}
	
	public String estadoEntidadView(boolean estadoEnti){
		String estadoEntidadActual = null;
		if(estadoEnti){
			estadoEntidadActual = "Activo";
		} else {
			estadoEntidadActual = "Inactivo";
		}
		return estadoEntidadActual;
	}

	public String colorestadoEntidadView(boolean estadoEnti){
		String estadoEntidadActual = null;
		if(estadoEnti){
			estadoEntidadActual = "#006600";
		} else {
			estadoEntidadActual = "#cc9900";
		}
		return estadoEntidadActual;
	}
	
	public Entidad getNuevaEntidad() {
		return nuevaEntidad;
	}

	public void setNuevaEntidad(Entidad nuevaEntidad) {
		this.nuevaEntidad = nuevaEntidad;
	}

	public String getIdEntidadParam() {
		return idEntidadParam;
	}

	public void setIdEntidadParam(String idEntidadParam) {
		this.idEntidadParam = idEntidadParam;
	}

	public List<Entidad> getEntidadList() {
		return entidadList;
	}

	public void setEntidadList(List<Entidad> entidadList) {
		this.entidadList = entidadList;
	}
	
}
