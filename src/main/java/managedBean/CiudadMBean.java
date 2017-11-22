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
import model.Ciudad;
import service.CiudadService;

@ViewScoped
@ManagedBean
public class CiudadMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ciudad nuevaCiudad;
	@Inject
	private FacesContext context;
	@Inject
	private CiudadService ciudadService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idCiudad;
	
	private ExternalContext externalContext;
	
	private List<Ciudad> ciudadList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idCiudad != null){
				limpiar();
				Integer id = Integer.parseInt(idCiudad);
				nuevaCiudad = ciudadService.getCiudad(id);
			} else {
				limpiar();
				listarCiudad();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarCiudad(String usuario){
		try {
			if(idCiudad != null){
				ciudadService.actualizarCiudad(nuevaCiudad);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizacion de Ciudad "+nuevaCiudad.getCiudCiudad(), "Ciudad", usuario);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/listadociudad.xhtml");
			} else {
				ciudadService.registrarCiudad(nuevaCiudad);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				auditoriaClass.agregarAuditoria("Actualizacion de Ciudad "+nuevaCiudad.getCiudCiudad(), "Ciudad", usuario);
				context.addMessage(null, m);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public void limpiar(){
		nuevaCiudad = new Ciudad();
	}
	
	public void listarCiudad(){
		ciudadList = ciudadService.listCiudad();
	}

	public Ciudad getNuevaCiudad() {
		return nuevaCiudad;
	}

	public void setNuevaCiudad(Ciudad nuevaCiudad) {
		this.nuevaCiudad = nuevaCiudad;
	}

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}

	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}
	
}
