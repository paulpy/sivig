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

import model.Pieza;
import service.PiezaService;

@ViewScoped
@ManagedBean
public class PiezasMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pieza nuevaPieza;
	@Inject
	private FacesContext context;
	@Inject
	private PiezaService piezaService;
	@RequestParam
	private String idPiezaParam;
	
	private ExternalContext externalContext;
	
	private List<Pieza> piezaList;
	
	public void inicializar(){
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idPiezaParam != null){
				limpiar();
				Integer id = Integer.parseInt(idPiezaParam);
				nuevaPieza = piezaService.getPieza(id);
			} else {
				limpiar();
				listarPiezas();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void guardarPieza(){
		try {
			if(idPiezaParam != null){
				piezaService.actualizarPieza(nuevaPieza);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/procesostecnicos/listapiezas.xhtml");
			} else {
				piezaService.registrarPieza(nuevaPieza);
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
	public void limpiar(){
		nuevaPieza = new Pieza();
	}
	
	public void listarPiezas(){
		piezaList = piezaService.listPieza();
	}

	public Pieza getNuevaPieza() {
		return nuevaPieza;
	}

	public void setNuevaPieza(Pieza nuevaPieza) {
		this.nuevaPieza = nuevaPieza;
	}

	public String getIdPiezaParam() {
		return idPiezaParam;
	}

	public void setIdPiezaParam(String idPiezaParam) {
		this.idPiezaParam = idPiezaParam;
	}

	public List<Pieza> getPiezaList() {
		return piezaList;
	}

	public void setPiezaList(List<Pieza> piezaList) {
		this.piezaList = piezaList;
	}
	
	
}
