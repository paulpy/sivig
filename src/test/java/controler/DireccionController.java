package controler;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Direccion;
import service.DireccionService;

@Model
public class DireccionController {
	@Inject
	private FacesContext context;
	@Inject
	private DireccionService direccionService;
	@Produces
	@Named
	private Direccion nuevaDireccion;
	public void initNuevaDireccion(){
		nuevaDireccion = new Direccion();
	}
	public void registrarDireccion() throws Exception{
		try {
			direccionService.registrarDireccion(nuevaDireccion);
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
