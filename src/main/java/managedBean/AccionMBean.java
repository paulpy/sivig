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
import model.Accion;
import service.AccionService;

@ViewScoped
@ManagedBean
public class AccionMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Accion nuevaAccion;
	@Inject
	private FacesContext context;
	@Inject
	private AccionService accionService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idAccion;
	private ExternalContext externalContext;
	private List<Accion> accionList;
	public void inicializar() {
		if(!FacesContext.getCurrentInstance().isPostback()){
			if(idAccion != null){
				limpiar();
				Integer id = Integer.parseInt(idAccion);
				nuevaAccion = accionService.getAccion(id);
			} else {
				limpiar();
				listarAccion();
			}
		}
	}
	public void guardarAccion(String usuariodata){
		try {
			if(idAccion != null){
				accionService.actualizaAccion(nuevaAccion);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/listadoacciones.xhtml");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando", "Accion", usuariodata);
			} else {
				accionService.registrarAccion(nuevaAccion);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				auditoriaClass.agregarAuditoria("Agregando Accion", "Accion", usuariodata);
				context.addMessage(null, m);
				inicializar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	public void listarAccion(){
		accionList = accionService.listAccion();
	}
	public void limpiar(){
		nuevaAccion = new Accion();
	}
	public Accion getNuevaAccion() {
		return nuevaAccion;
	}
	public void setNuevaAccion(Accion nuevaAccion) {
		this.nuevaAccion = nuevaAccion;
	}
	public String getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(String idAccion) {
		this.idAccion = idAccion;
	}
	public List<Accion> getAccionList() {
		return accionList;
	}
	public void setAccionList(List<Accion> accionList) {
		this.accionList = accionList;
	}
}
