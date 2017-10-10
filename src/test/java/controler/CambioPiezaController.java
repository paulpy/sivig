package controler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.CambioPieza;
import model.Equipo;
import model.Funcionario;
import model.Pieza;
import service.CambioPiezaService;
import service.EquipoService;
import service.FuncionarioService;
import service.PiezaService;

@Model
public class CambioPiezaController {
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
	@Produces
	@Named
	private CambioPieza nuevoCambioPieza;
	private List<Funcionario> funcionarioList;
	private List<Equipo> equipoList;
	private List<Pieza> piezaList;
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
	@PostConstruct
	public void initNuevoCambioPieza(){
		nuevoCambioPieza = new CambioPieza();
		funcionarioList = funcionarioService.listFuncionario();
		equipoList = equipoService.listEquipo();
		piezaList = piezaService.listPieza();
	}
	public void registrarCambioPieza() throws Exception{
		try {
			cambioPiezaService.registrarCambioPieza(nuevoCambioPieza);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado","Confirmacion de Registro");
			context.addMessage(null, m);
		} catch (Exception e) {
			// TODO: handle exception.
			String mensajeErroneo = e.toString();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeErroneo, "falla al registrar");
            context.addMessage(null, m);
		}
	}
}
