package managedBean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Funcionario;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class ReportesSistemaMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private FacesContext context;
	
	private List<Funcionario> funcionarioTecList;
	private Date fechaInicio;
	private Date fechaFin;
	private Funcionario funcionarioSelect;
	private Map<String, Object> parametros;
	
	public void inicializar(){
		listar();
	}
	
	public void generarReporte(){
		if(fechaInicio == null){
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Falta Ingresar Fecha Inicio");
			context.addMessage(null, m);
		} else {
			if(fechaFin == null){
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Falta Ingresar Fecha Fin");
				context.addMessage(null, m);
			} else {
				if(funcionarioSelect == null){
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Atencion","Se generara el Reporte sin filtro de Funcionario");
					context.addMessage(null, m);
					parametros = new HashMap<String, Object>();
					parametros.put("fechainicio", fechaInicio);
					parametros.put("fechafin", fechaFin);
				} else {
					parametros = new HashMap<String, Object>();
					parametros.put("fechainicio", fechaInicio);
					parametros.put("fechafin", fechaFin);
					parametros.put("idfuncionario", funcionarioSelect.getFuncIdFuncionario());
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Atencion","Se generara el Reporte ");
					context.addMessage(null, m);
				}
			}
		}
	}
	
	public void listar(){
		funcionarioTecList = funcionarioService.listFuncionarioTec();
	}

	public List<Funcionario> getFuncionarioTecList() {
		return funcionarioTecList;
	}

	public void setFuncionarioTecList(List<Funcionario> funcionarioTecList) {
		this.funcionarioTecList = funcionarioTecList;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Funcionario getFuncionarioSelect() {
		return funcionarioSelect;
	}

	public void setFuncionarioSelect(Funcionario funcionarioSelect) {
		this.funcionarioSelect = funcionarioSelect;
	}
	
	

}
