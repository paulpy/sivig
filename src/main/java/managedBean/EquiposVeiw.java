package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import model.Equipo;
import service.EquipoService;

@Model
@ManagedBean
@ViewScoped
public class EquiposVeiw implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Equipo> equipos;
	
	private Equipo selectEquipo;
	
	@Inject
	private EquipoService equipoService;
	
	@PostConstruct
	public void init(){
		equipos = equipoService.listEquipo();
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public void setEquipoService(EquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public Equipo getSelectEquipo() {
		return selectEquipo;
	}

	public void setSelectEquipo(Equipo selectEquipo) {
		this.selectEquipo = selectEquipo;
	}
	
}
