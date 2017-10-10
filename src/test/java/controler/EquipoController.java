package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Equipo;
import model.Raspberry;
import service.EquipoService;
import service.RaspberryService;

@Model
@ViewScoped
public class EquipoController {
	@Inject
	private FacesContext context;
	@Inject
	private EquipoService equipoService;
	@Inject
	private RaspberryService raspberryService;
	@Produces
	@Named
	private Equipo nuevoEquipo;
	private List<Raspberry> raspberriesList;
	private List<Equipo> equipoList; 
	public List<Equipo> getEquipoList() {
		return equipoList;
	}
	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}
	public List<Raspberry> getRaspberriesList() {
		return raspberriesList;
	}
	public void setRaspberriesList(List<Raspberry> raspberriesList) {
		this.raspberriesList = raspberriesList;
	}
	@PostConstruct
	public void initNuevoEquipo(){
		nuevoEquipo = new Equipo();
		raspberriesList = raspberryService.listRaspberry();
		equipoList = equipoService.listEquipo();
	}
	public void registrarEquipo() throws Exception {
		try {
			equipoService.registrarEquipo(nuevoEquipo);
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
