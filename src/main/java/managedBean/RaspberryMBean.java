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
import model.Raspberry;
import service.RaspberryService;

@ViewScoped
@ManagedBean
public class RaspberryMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Raspberry nuevoRaspberry;
	@Inject
	private FacesContext context;
	@Inject
	private RaspberryService raspberryService;
	@Inject
	private AuditoriaClass auditoriaClass;
	@RequestParam
	private String idRaspberryParam;
	
	private ExternalContext externalContext;
	
	private List<Raspberry> raspberryList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idRaspberryParam != null){
				limpiar();
				Integer id = Integer.parseInt(idRaspberryParam);
				nuevoRaspberry = raspberryService.getRaspberry(id);
			} else {
				limpiar();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarRaspberry(String usuariodata){
		try {
			if(idRaspberryParam != null){
				raspberryService.actualizarRaspberry(nuevoRaspberry);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Actualizando Raspberry "+nuevoRaspberry.getRaspNombre(), "Raspberry", usuariodata);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/equipos/datos/estadoraspberrys.xhtml");
			} else {
				raspberryService.registrarRaspberry(nuevoRaspberry);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				auditoriaClass.agregarAuditoria("Agregando Raspberry "+nuevoRaspberry.getRaspNombre(), "Raspberry", usuariodata);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public String estadoRaspberryView(boolean estadoRaspberryActual){
		String estadoRaspberry = null;
		if(estadoRaspberryActual){
			estadoRaspberry = "Habilitado";
		} else {
			estadoRaspberry = "Inhabilitado";
		}
		return estadoRaspberry;
	}
	
	public String colorEstadoEquipoView(boolean estadoRaspberryActual){
		String estadoRaspberry = null;
		if(estadoRaspberryActual){
			estadoRaspberry = "Green";
		} else {
			estadoRaspberry = "Red";
		}
		return estadoRaspberry;
	}
	
	public void listarRaspberrys(){
		raspberryList = raspberryService.listRaspberry();
	}
	
	public void limpiar(){
		nuevoRaspberry = new Raspberry();
	}

	public Raspberry getNuevoRaspberry() {
		return nuevoRaspberry;
	}

	public void setNuevoRaspberry(Raspberry nuevoRaspberry) {
		this.nuevoRaspberry = nuevoRaspberry;
	}

	public String getIdRaspberryParam() {
		return idRaspberryParam;
	}

	public void setIdRaspberryParam(String idRaspberryParam) {
		this.idRaspberryParam = idRaspberryParam;
	}

	public List<Raspberry> getRaspberryList() {
		return raspberryList;
	}

	public void setRaspberryList(List<Raspberry> raspberryList) {
		this.raspberryList = raspberryList;
	}
	
	
}
