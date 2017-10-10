package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Ciudad;
import service.CiudadService;

@Model
public class CiudadController {
	@Inject
	private FacesContext context;
	@Inject
	private CiudadService ciudadService;
	@Produces
	@Named
	private Ciudad nuevaCiudad;
	private List<Ciudad> ciudadList;
	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}
	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}
	@PostConstruct
	public void initNuevaCiudad(){
		nuevaCiudad = new Ciudad();
		ciudadList = ciudadService.listCiudad();
	}
	public void registrarCiudad() throws Exception{
		try {
			ciudadService.registrarCiudad(nuevaCiudad);
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
