package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.solder.servlet.http.RequestParam;

import model.CausaCambioEstado;
import service.CambioEstadoService;

@ViewScoped
@ManagedBean
public class CausaCambioMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CausaCambioEstado nuevaCambioEstado;
	@Inject
	private FacesContext context;
	@Inject
	private CambioEstadoService cambioEstadoService;
	@RequestParam
	private String idCausaCambio;
	
	private ExternalContext externalContext;
	
	private List<CausaCambioEstado> causaCambioEstadoList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idCausaCambio != null){
				limpiar();
				Integer id = Integer.parseInt(idCausaCambio);
				nuevaCambioEstado = cambioEstadoService.getCambioEstado(id);
			} else {
				limpiar();
				listarCausaCambio();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarCambioEstado(){
		try {
			if(idCausaCambio != null){
				cambioEstadoService.actualizarCambioEstado(nuevaCambioEstado);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/sistema/datosgenericos/listadocausaestado.xhtml");
			} else {
				cambioEstadoService.registrarCambioEstado(nuevaCambioEstado);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		}
	}
	
	public void listarCausaCambio(){
		causaCambioEstadoList = cambioEstadoService.listCausaCambioEstado();
	}
	
	public void limpiar(){
		nuevaCambioEstado = new CausaCambioEstado();
	}

	public CausaCambioEstado getNuevaCambioEstado() {
		return nuevaCambioEstado;
	}

	public void setNuevaCambioEstado(CausaCambioEstado nuevaCambioEstado) {
		this.nuevaCambioEstado = nuevaCambioEstado;
	}

	public String getIdCausaCambio() {
		return idCausaCambio;
	}

	public void setIdCausaCambio(String idCausaCambio) {
		this.idCausaCambio = idCausaCambio;
	}

	public List<CausaCambioEstado> getCausaCambioEstadoList() {
		return causaCambioEstadoList;
	}

	public void setCausaCambioEstadoList(List<CausaCambioEstado> causaCambioEstadoList) {
		this.causaCambioEstadoList = causaCambioEstadoList;
	}
	
	
}
