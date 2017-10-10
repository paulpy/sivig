package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import model.Equipo;
import model.Raspberry;
import service.EquipoService;
import service.RaspberryService;

@ViewScoped
@ManagedBean
public class ViewEquiposRaspMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EquipoService equipoService;
	@Inject
	private RaspberryService raspberryService;
	
	
	private List<Equipo> equipos;
	private List<Raspberry> raspberrys;
	
	public void listEquipos(){
		equipos = equipoService.listEquipoActivos();
	}
	
	public void listRaspberrys(){
		raspberrys = raspberryService.listRaspberry();
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Raspberry> getRaspberrys() {
		return raspberrys;
	}

	public void setRaspberrys(List<Raspberry> raspberrys) {
		this.raspberrys = raspberrys;
	}
	
	

}
