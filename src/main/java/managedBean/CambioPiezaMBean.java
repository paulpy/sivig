package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.CambioPieza;
import model.Equipo;
import model.Funcionario;
import model.Pieza;
import service.CambioPiezaService;
import service.EquipoService;
import service.FuncionarioService;
import service.PiezaService;

@ViewScoped
@ManagedBean
public class CambioPiezaMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CambioPieza nuevoCambioPieza;
	@Inject
	private FacesContext context;
	@Inject
	private CambioPiezaService cambioPiezaService;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private EquipoService equipoService;
	@Inject
	private PiezaService piezaService;
	
	private List<Funcionario> funcionarioList;
	private List<Equipo> equipoList;
	private List<Pieza> piezaList;
	
	public void inicializar(){
		limpiar();
		listar();
	}
	
	public void registrarCambioPieza() throws Exception{
		try {
			cambioPiezaService.registrarCambioPieza(nuevoCambioPieza);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
			limpiar();
		} catch (Exception e) {
			// TODO: handle exception.
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
	
	public void limpiar(){
		nuevoCambioPieza = new CambioPieza();
	}
	
	public void listar(){
		funcionarioList = funcionarioService.listFuncionario();
		equipoList = equipoService.listEquipo();
		piezaList = piezaService.listPieza();
	}

	public CambioPieza getNuevoCambioPieza() {
		return nuevoCambioPieza;
	}

	public void setNuevoCambioPieza(CambioPieza nuevoCambioPieza) {
		this.nuevoCambioPieza = nuevoCambioPieza;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public List<Equipo> getEquipoList() {
		return equipoList;
	}

	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}

	public List<Pieza> getPiezaList() {
		return piezaList;
	}

	public void setPiezaList(List<Pieza> piezaList) {
		this.piezaList = piezaList;
	}
	
}
