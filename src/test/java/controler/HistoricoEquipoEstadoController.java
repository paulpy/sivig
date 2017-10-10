package controler;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.HistoricoEquipoEstado;
import service.HistoricoEquipoEstadoService;

@Model
public class HistoricoEquipoEstadoController {
	@Inject
	private FacesContext context;
	@Inject
	private HistoricoEquipoEstadoService historicoEquipoEstadoService;
	@Produces
	@Named
	private HistoricoEquipoEstado nuevoHistoricoEquipoEstado;
	@PostConstruct
	public void initNuevoHistoricoEquipoEstado(){
		nuevoHistoricoEquipoEstado = new HistoricoEquipoEstado();
	}
	public void registrarHistoricoEquipoEstado() throws Exception{
		try {
			historicoEquipoEstadoService.registrarHistoricoEquipoEstado(nuevoHistoricoEquipoEstado);
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
