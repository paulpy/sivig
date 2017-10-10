package controler;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Equipo;
import model.Raspberry;
import service.RaspberryService;

@Model
public class RaspberryController {
	@Inject
	private FacesContext context;
	@Inject
	private RaspberryService raspberryService;
	@Produces
	@Named
	private Raspberry nuevoRaspberry;
	private List<Raspberry> raspberryList;
	public List<Raspberry> getRaspberryList() {
		return raspberryList;
	}
	public void setRaspberryList(List<Raspberry> raspberryList) {
		this.raspberryList = raspberryList;
	}
	@Produces
	@Named
	private Equipo nuevoEquipoR;
	@PostConstruct
	public void initNuevaRaspberry(){
		nuevoRaspberry = new Raspberry();
		nuevoEquipoR = new Equipo();
		raspberryList = raspberryService.listRaspberry();
	}
	public void registrarRaspberry() throws Exception {
		try {
			raspberryService.registrarRaspberry(nuevoRaspberry);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
	public void registrarConjunto() throws ExecutionException {
		try {
			if(raspberryService.insertConjunto(nuevoEquipoR, nuevoRaspberry)){
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
			} else {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Fallo el Registro");
				context.addMessage(null, m);
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
}