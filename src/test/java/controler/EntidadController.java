package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Entidad;
import service.EntidadService;

@Model
public class EntidadController {
	@Inject
	private FacesContext context;
	@Inject
	private EntidadService entidadService;
	@Produces
	@Named
	private Entidad nuevaEntidad;
	private List<Entidad> entidadList;
	public List<Entidad> getEntidadList() {
		return entidadList;
	}
	public void setEntidadList(List<Entidad> entidadList) {
		this.entidadList = entidadList;
	}
	@PostConstruct
	public void initNuevaEntidad(){
		nuevaEntidad = new Entidad();
		entidadList = entidadService.listEntidad();
	}
	public void registrarEntidad() throws Exception{
		try {
			entidadService.registrarEntidad(nuevaEntidad);
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
