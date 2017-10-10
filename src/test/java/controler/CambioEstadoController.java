package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.CausaCambioEstado;
import service.CambioEstadoService;

@Model
public class CambioEstadoController {
	
	@Inject
	private FacesContext context;
	@Inject
	private CambioEstadoService cambioEstadoService;
	@Produces
	@Named
	private CausaCambioEstado nuevoCambioEstado;
	private List<CausaCambioEstado> causaCambioEstadoList;
	public List<CausaCambioEstado> getCausaCambioEstadoList() {
		return causaCambioEstadoList;
	}

	public void setCausaCambioEstadoList(List<CausaCambioEstado> causaCambioEstadoList) {
		this.causaCambioEstadoList = causaCambioEstadoList;
	}

	@PostConstruct
	public void initNuevoCausaCambioEstado(){
		nuevoCambioEstado = new CausaCambioEstado();
		causaCambioEstadoList = cambioEstadoService.listCausaCambioEstado();
	}
	
	public void registrarCambioestado() throws Exception{
		try {
			cambioEstadoService.registrarCambioEstado(nuevoCambioEstado);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensajeErroneo,"Confirmacion de Registro");
			context.addMessage(null, m);
		}
	}
}
