package controler;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Auditoria;
import service.AuditoriaService;

@Model
public class AuditoriaController {
	@Inject
	private FacesContext context;
	@Inject
	private AuditoriaService auditoriaService;
	@Produces
	@Named
	private Auditoria nuevaAuditoria;
	@PostConstruct
	public void initNuevaAuditoria(){
		nuevaAuditoria = new Auditoria();
	}
	public void registrarAuditoria() throws Exception{
		try {
			auditoriaService.registrarAuditoria(nuevaAuditoria);
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
