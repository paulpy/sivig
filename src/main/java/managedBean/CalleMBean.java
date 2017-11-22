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
import service.CalleService;

@ViewScoped
@ManagedBean
public class CalleMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calle nuevaCalle;
	@Inject
	private FacesContext context;
	@Inject
	private CalleService calleService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idCalle;
	
	private ExternalContext externalContext;
	
	private List<Calle> calleList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idCalle != null){
				limpiar();
				Integer id = Integer.parseInt(idCalle);
				nuevaCalle = calleService.getCalle(id);
			} else {
				limpiar();
				listarCalles();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarCalle(String usuario){
		try {
			if(idCalle != null){
				calleService.actualizarCalle(nuevaCalle);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando Calle "+nuevaCalle.getCallCalle(), "calle", usuario);
				inicializar();
			} else {
				calleService.registrarCalle(nuevaCalle);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando Calle "+nuevaCalle.getCallCalle(), "calle", usuario);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/agregarcalle.xhtml");
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public void listarCalles(){
		calleList = calleService.listCalle();
	}
	
	public void limpiar(){
		nuevaCalle = new Calle();
	}

	public Calle getNuevaCalle() {
		return nuevaCalle;
	}

	public void setNuevaCalle(Calle nuevaCalle) {
		this.nuevaCalle = nuevaCalle;
	}

	public String getIdCalle() {
		return idCalle;
	}

	public void setIdCalle(String idCalle) {
		this.idCalle = idCalle;
	}

	public List<Calle> getCalleList() {
		return calleList;
	}

	public void setCalleList(List<Calle> calleList) {
		this.calleList = calleList;
	}
	
	
}
