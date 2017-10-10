package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Pieza;
import service.PiezaService;

@Model
public class PiezaController {
	@Inject
	private FacesContext context;
	@Inject
	private PiezaService piezaService;
	@Produces
	@Named
	private Pieza nuevaPieza;
	private List<Pieza> piezaList;
	public List<Pieza> getPiezaList() {
		return piezaList;
	}
	public void setPiezaList(List<Pieza> piezaList) {
		this.piezaList = piezaList;
	}
	@PostConstruct
	public void initNuevaPieza(){
		nuevaPieza = new Pieza();
		piezaList = piezaService.listPieza();
	}
	public void registrarPieza() throws Exception {
		try {
			piezaService.registrarPieza(nuevaPieza);
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
