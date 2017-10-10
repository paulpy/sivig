package controler;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Persona;
import service.PersonaService;
@Model
public class PersonaController {
	@Inject
	private FacesContext context;
	@Inject
	private PersonaService personaService;
	@Produces
	@Named
	private Persona nuevaPersona;
	@PostConstruct
	public void initNuevaPersona(){
		nuevaPersona = new Persona();
	}
	public void registrarPersona() throws Exception {
		try {
			personaService.registrarPersona(nuevaPersona);
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
