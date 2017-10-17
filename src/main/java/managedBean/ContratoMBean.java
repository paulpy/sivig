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

import model.Contrato;
import model.Entidad;
import model.Funcionario;
import service.ContratoService;
import service.EntidadService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class ContratoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato nuevoContrato;
	@Inject
	private FacesContext context;
	@Inject
	private ContratoService contratoService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private EntidadService entidadService;
	@RequestParam
	private String idContratoParam;

	private ExternalContext externalContext;
	
	private List<Funcionario> funcionarioList;
	private List<Entidad> entidadList;
	private List<Contrato> contratoList;
	
	public void inicializar() throws Exception {
		if(!FacesContext.getCurrentInstance().isPostback()){
			if (idContratoParam != null) {
				nuevoContrato = new Contrato();
				listarEntidadFuncionario();
				Integer id = Integer.parseInt(idContratoParam);
				nuevoContrato = contratoService.getContrato(id);
			} else {
				limpiar();
				listarEntidadFuncionario();
			}
		}
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
	}

	public void guardarContrato() {
		try {
			if (idContratoParam != null) {
				contratoService.actualizarContrato(nuevoContrato);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado",
						"Confirmacion de Actualizacion");
				context.addMessage(null, m);
				externalContext.redirect(externalContext.getRequestContextPath() + "/protected/empresarial/clientes/estadocontratos.xhtml");
			} else {
				contratoService.registrarContrato(nuevoContrato);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Confirmacion de Registro");
				context.addMessage(null, m);
				limpiar();
			}
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
			context.addMessage(null, m);
		} finally {
			limpiar();
		}
	}

	public void limpiar() {
		nuevoContrato = new Contrato();
	}

	public void listarContratos() {
		contratoList = contratoService.listContrato();
	}

	public void listarEntidadFuncionario() {
		entidadList = entidadService.listEntidad();
		funcionarioList = funcionarioService.listFuncionario();
	}
	
	public String estadoContratoView(boolean estadoContratoOriginal){
		String estadoCont = null;
		if(estadoContratoOriginal){
			estadoCont = "Vigente";
		} else {
			estadoCont = "Inactivo";
		}
		return estadoCont;
	}
	
	public String colorEstadoContratoView(boolean estadoContratoOriginal){
		String estadoCont = null;
		if(estadoContratoOriginal){
			estadoCont = "#006600";
		} else {
			estadoCont = "#cc9900";
		}
		return estadoCont;
	}

	public String getIdContratoParam() {
		return idContratoParam;
	}

	public void setIdContratoParam(String idContratoParam) {
		this.idContratoParam = idContratoParam;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public List<Entidad> getEntidadList() {
		return entidadList;
	}

	public void setEntidadList(List<Entidad> entidadList) {
		this.entidadList = entidadList;
	}

	public List<Contrato> getContratoList() {
		return contratoList;
	}

	public void setContratoList(List<Contrato> contratoList) {
		this.contratoList = contratoList;
	}

	public Contrato getNuevoContrato() {
		return nuevoContrato;
	}

	public void setNuevoContrato(Contrato nuevoContrato) {
		this.nuevoContrato = nuevoContrato;
	}

}