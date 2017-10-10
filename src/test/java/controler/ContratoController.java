package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.solder.servlet.http.RequestParam;

import model.Contrato;
import model.Entidad;
import model.Funcionario;
import service.ContratoService;
import service.EntidadService;
import service.FuncionarioService;

@Model
public class ContratoController {
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
	@Produces
	@Named
	private Contrato nuevoContrato;
	@Produces
	@Named
	private Contrato oldContrato;
	private List<Funcionario> funcionarioList;
	private List<Entidad> entidadList;
	private List<Contrato> contratoList;
	
	@PostConstruct
	public void initNuevoContrato() {
		if(!FacesContext.getCurrentInstance().isPostback()){
			funcionarioList = funcionarioService.listFuncionario();
			entidadList = entidadService.listEntidad();
			contratoList = contratoService.listContrato();
			nuevoContrato = new Contrato();
		} /*else {
			try {
				inicializar();		
			} catch (Exception e) {
				// TODO: handle exception
				String mensajeErroneo = e.toString();
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "");
	            context.addMessage(null, m);
			}
		}*/
	}
	
	public void registrarContrato() throws Exception{
		try {
			contratoService.registrarContrato(nuevoContrato);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);			
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
		limpiar();
	}
	
	public void inicializar() throws Exception {
		if (idContratoParam != null) {
			nuevoContrato = new Contrato();
			Integer id = Integer.parseInt(idContratoParam);
			nuevoContrato = contratoService.getContrato(id);
		} else {
			limpiar();
		}
	}
	
	private void limpiar(){
		nuevoContrato = new Contrato();
	}
	
	public void actualizarContrato() throws Exception{
		try {
			contratoService.actualizarContrato(nuevoContrato);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizado","Confirmacion de Actualizacion");
			context.addMessage(null, m);
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
		limpiar();
	}
	
	public void guardarContrato (){
		try {
			if(idContratoParam != null){
				contratoService.actualizarContrato(nuevoContrato);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizado","Confirmacion de Actualizacion");
				context.addMessage(null, m);
			} else {
				contratoService.registrarContrato(nuevoContrato);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
				context.addMessage(null, m);	
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
	
	public List<Contrato> getContratoList() {
		return contratoList;
	}
	public void setContratoList(List<Contrato> contratoList) {
		this.contratoList = contratoList;
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
	public String getIdContratoParam() {
		return idContratoParam;
	}
	public void setIdContratoParam(String idContratoParam) {
		this.idContratoParam = idContratoParam;
	}
}
