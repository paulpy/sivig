package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import model.Equipo;
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
				if(listadoEstadoHE.size() == 0){
					System.out.println("Es un estado null");
				} else {
					String estadoEquipo = listadoEstadoHE.get(0).toString();
					equipo.setEquiEstado(estadoEquipo);
					equipoService.updateEquipo(equipo);
				}
				List<String> listadoEstadoHR = historicoRaspberryEstadoService.listHistoricoRaspCambioEstado(equipo.getRaspberry().getRaspIdRaspberry());
				if(listadoEstadoHR.size() == 0){
					System.out.println("Es un estado null");
				} else {
					String estadoRaspberry = listadoEstadoHR.get(0).toString();
					Raspberry raspactual = equipo.getRaspberry();
					raspactual.setRaspEstado(estadoRaspberry);
					raspberryService.actualizarRaspberry(raspactual);
				}
			}
			RequestContext.getCurrentInstance().update("mainForm:gritEquiposMontGral");
		}
		
	}
	
	public String estadoRaspberryView(boolean estadoRaspberryActual){
		String estadoRaspberry = null;
		if(estadoRaspberryActual){
			estadoRaspberry = "Habilitado";
		} else {
			estadoRaspberry = "Inhabilitado";
		}
		return estadoRaspberry;
	}
	
	public String colorEstadoEquipoView(boolean estadoRaspberryActual){
		String estadoRaspberry = null;
		if(estadoRaspberryActual){
			estadoRaspberry = "Green";
		} else {
			estadoRaspberry = "Red";
		}
		return estadoRaspberry;
	}
	
	public String colorEstadoEquipos(String estado){
		String colorAsignado = null;
		if((estado.equals("Corriendo"))||(estado.equals("Encendido"))){
			colorAsignado = "Green";
		} else {
			if((estado.equals("Reiniciando"))||(estado.equals("Sistema Operativo Iniciando"))){
				colorAsignado = "Orange";
			} else {
				if (estado.equals("Apagado")){
					colorAsignado = "Red";
				}
			}
		}
		
		return colorAsignado;
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
