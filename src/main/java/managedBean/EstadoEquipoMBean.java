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

import model.EstadoEquipo;
import service.EstadoEquipoService;

@ViewScoped
@ManagedBean
public class EstadoEquipoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EstadoEquipo nuevoEstadoEquipo;
	@Inject
	private FacesContext context;
	@Inject
	private EstadoEquipoService estadoEquipoService;
	@RequestParam
	private String idEstadoEquipo;
	
	private ExternalContext externalContext;
	
	private List<EstadoEquipo> estadoEquipoList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idEstadoEquipo != null){
				limpiar();
				Integer id = Integer.parseInt(idEstadoEquipo);
				nuevoEstadoEquipo = estadoEquipoService.getEstadoEquipo(id);			
			} else {
				limpiar();
				listarEstadoEquipo();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarEstadoEquipo(){
		try {
			if(idEstadoEquipo != null){
				estadoEquipoService.actualizarEstadoEquipo(nuevoEstadoEquipo);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/listadoestadoequipo.xhtml");
			} else {
				estadoEquipoService.registrarEstadoEquipo(nuevoEstadoEquipo);
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
	
	public void listarEstadoEquipo(){
		estadoEquipoList = estadoEquipoService.listEstadoEquipos();
	}
	
	public void limpiar(){
		nuevoEstadoEquipo = new EstadoEquipo();
	}

	public EstadoEquipo getNuevoEstadoEquipo() {
		return nuevoEstadoEquipo;
	}

	public void setNuevoEstadoEquipo(EstadoEquipo nuevoEstadoEquipo) {
		this.nuevoEstadoEquipo = nuevoEstadoEquipo;
	}

	public String getIdEstadoEquipo() {
		return idEstadoEquipo;
	}

	public void setIdEstadoEquipo(String idEstadoEquipo) {
		this.idEstadoEquipo = idEstadoEquipo;
	}

	public List<EstadoEquipo> getEstadoEquipoList() {
		return estadoEquipoList;
	}

	public void setEstadoEquipoList(List<EstadoEquipo> estadoEquipoList) {
		this.estadoEquipoList = estadoEquipoList;
	}
	
	
}
