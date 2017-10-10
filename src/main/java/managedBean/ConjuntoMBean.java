package managedBean;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Equipo;
import model.Raspberry;
import service.RaspberryService;

@ViewScoped
@ManagedBean
public class ConjuntoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Raspberry nuevoRaspberry;
	private Equipo nuevoEquipo;
	
	@Inject
	private FacesContext context;
	@Inject
	private RaspberryService raspberryService;
	
	public void inicializar(){
		nuevoRaspberry = new Raspberry();
		nuevoEquipo = new Equipo();
	}
	
	public void registrarConjunto() throws ExecutionException {
		try {
			if(raspberryService.insertConjunto(nuevoEquipo, nuevoRaspberry)){
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				inicializar();
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

	public Raspberry getNuevoRaspberry() {
		return nuevoRaspberry;
	}

	public void setNuevoRaspberry(Raspberry nuevoRaspberry) {
		this.nuevoRaspberry = nuevoRaspberry;
	}

	public Equipo getNuevoEquipo() {
		return nuevoEquipo;
	}

	public void setNuevoEquipo(Equipo nuevoEquipo) {
		this.nuevoEquipo = nuevoEquipo;
	}
	
	
}
