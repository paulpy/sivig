package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Calle;
import service.CalleService;

@Model
public class CalleController {
	@Inject
	private FacesContext context;
	@Inject
	private CalleService calleService;
	@Produces
	@Named
	private Calle nuevaCalle;
	private List<Calle> calleList;
	public List<Calle> getCalleList() {
		return calleList;
	}
	public void setCalleList(List<Calle> calleList) {
		this.calleList = calleList;
	}
	@PostConstruct
	public void initNuevaCalle(){
		nuevaCalle = new Calle();
		calleList = calleService.listCalle();
	}
	public void registrarCalle() throws Exception {
		try {
			calleService.registrarCalle(nuevaCalle);
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
