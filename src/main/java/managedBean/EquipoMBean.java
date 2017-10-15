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

import model.Equipo;

import model.Raspberry;
import service.EquipoService;
import service.RaspberryService;

@ViewScoped
@ManagedBean
public class EquipoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipo nuevoEquipo;
	
	@Inject
	private FacesContext context;
	@Inject
	private EquipoService equipoService;
	@Inject
	private RaspberryService raspberryService;
	@RequestParam
	private String idEquipoParam;
	
	private ExternalContext externalContext;
	
	private List<Raspberry> raspberrisList;
	private List<Equipo> equipoList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idEquipoParam != null){
				limpiar();
				Integer id = Integer.parseInt(idEquipoParam);
				nuevoEquipo = equipoService.getEquipo(id);
			} else {
				limpiar();
				listarEquipos();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarEquipo(){
		try {
			if(idEquipoParam != null){
				equipoService.updateEquipo(nuevoEquipo);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/equipos/datos/estadoequipos.xhtml");
			} else {
				equipoService.registrarEquipo(nuevoEquipo);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
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
		nuevoEquipo = new Equipo();
		listarRaspoberrys();
	}
	
	public String estadoEquipoView(boolean estadoEquipoActual){
		String estadoEquipo = null;
		if(estadoEquipoActual){
			estadoEquipo = "Habilitado";
		} else {
			estadoEquipo = "Inhabilitado";
		}
		return estadoEquipo;
	}
	
	public String colorEstadoEquipoView(boolean estadoEquipoActual){
		String estadoEquipo = null;
		if(estadoEquipoActual){
			estadoEquipo = "Green";
		} else {
			estadoEquipo = "Red";
		}
		return estadoEquipo;
	}
	
	public void listarEquipos(){
		equipoList = equipoService.listEquipo();
	}
	
	public void listarRaspoberrys(){
		raspberrisList = raspberryService.listRaspberry();
	}

	public Equipo getNuevoEquipo() {
		return nuevoEquipo;
	}

	public void setNuevoEquipo(Equipo nuevoEquipo) {
		this.nuevoEquipo = nuevoEquipo;
	}

	public String getIdEquipoParam() {
		return idEquipoParam;
	}

	public void setIdEquipoParam(String idEquipoParam) {
		this.idEquipoParam = idEquipoParam;
	}

	public List<Raspberry> getRaspberrisList() {
		return raspberrisList;
	}

	public void setRaspberrisList(List<Raspberry> raspberrisList) {
		this.raspberrisList = raspberrisList;
	}

	public List<Equipo> getEquipoList() {
		return equipoList;
	}

	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}
	
	
}
