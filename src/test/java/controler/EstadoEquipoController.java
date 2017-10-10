package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.EstadoEquipo;
import service.EstadoEquipoService;

@Model
public class EstadoEquipoController {
	@Inject
	private FacesContext context;
	@Inject
	private EstadoEquipoService estadoEquipoService;
	@Produces
	@Named
	private EstadoEquipo nuevoEstadoEquipo;
	private List<EstadoEquipo> estadoEquipoList;
	public List<EstadoEquipo> getEstadoEquipoList() {
		return estadoEquipoList;
	}
	public void setEstadoEquipoList(List<EstadoEquipo> estadoEquipoList) {
		this.estadoEquipoList = estadoEquipoList;
	}
	@PostConstruct
	public void initNuevoEstadoEquipo(){
		nuevoEstadoEquipo = new EstadoEquipo();
		estadoEquipoList = estadoEquipoService.listEstadoEquipos();
	}
	public void registrarEstadoEquipo() throws Exception {
		try {
			estadoEquipoService.registrarEstadoEquipo(nuevoEstadoEquipo);
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
