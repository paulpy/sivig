package controler;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.HistoricoRaspberryEstado;
import service.HistoricoRaspberryEstadoService;

public class HistoricoRaspberryEstadoController {
	@Inject
	private FacesContext context;
	@Inject
	private HistoricoRaspberryEstadoService historicoRaspberryEstadoService;
	@Produces
	@Named
	private HistoricoRaspberryEstado nuevoHistoricoRaspbrryEstado;
	@PostConstruct
	public void initNuevoHistoricoRaspberryEstado(){
		nuevoHistoricoRaspbrryEstado = new HistoricoRaspberryEstado();
	}
	public void registrarHistoricoRaspberryEstado() throws Exception{
		try {
			historicoRaspberryEstadoService.registrarHistoricoRaspberryEstadoService(nuevoHistoricoRaspbrryEstado);;
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
