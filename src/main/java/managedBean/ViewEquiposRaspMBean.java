package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import model.Equipo;
import model.HistoricoEquipoEstado;
import model.HistoricoRaspberryEstado;
import model.Raspberry;
import service.EquipoService;
import service.HistoricoEquipoEstadoService;
import service.HistoricoRaspberryEstadoService;
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
	@Inject
	private HistoricoEquipoEstadoService historicoEquipoEstadoService;
	@Inject
	private HistoricoRaspberryEstadoService historicoRaspberryEstadoService;
	
	
	private List<Equipo> equipos;
	private List<Raspberry> raspberrys;
	
	public void listEquipos(){
		equipos = equipoService.listEquipoActivos();
	}
	
	public void listRaspberrys(){
		raspberrys = raspberryService.listRaspberry();
	}
	
	public void estadosEquipos() throws Exception{
		equipos = equipoService.listEquipoActivos();
		if(equipos.size() > 0){
			for(Equipo equipo : equipos){
				List<String> listadoEstadoHE = historicoEquipoEstadoService.listHistoricoEquipoCambioEstado(equipo.getEquiIdEquipo());
				String estadoEquipo = listadoEstadoHE.get(0).toString();
				equipo.setEquiEstado(estadoEquipo);
				equipoService.updateEquipo(equipo);
				List<String> listadoEstadoHR = historicoRaspberryEstadoService.listHistoricoRaspCambioEstado(equipo.getRaspberry().getRaspIdRaspberry());
				String estadoRaspberry = listadoEstadoHR.get(0).toString();
				Raspberry raspactual = equipo.getRaspberry();
				raspactual.setRaspEstado(estadoRaspberry);
				raspberryService.actualizarRaspberry(raspactual);
			}
			RequestContext.getCurrentInstance().update("mainForm:gritEquiposMontGral");
		}
		
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
