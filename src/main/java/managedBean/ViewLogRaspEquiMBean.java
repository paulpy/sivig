package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import model.HistoricoEquipoEstado;
import model.HistoricoRaspberryEstado;
import service.HistoricoEquipoEstadoService;
import service.HistoricoRaspberryEstadoService;

@ViewScoped
@ManagedBean
public class ViewLogRaspEquiMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricoEquipoEstadoService historicoEquipoEstadoService;
	@Inject
	private HistoricoRaspberryEstadoService historicoRaspberryEstadoService;
	
	private List<HistoricoEquipoEstado> histEE;
	private List<HistoricoRaspberryEstado> histRE;
	
	public void listarLogs(){
		histEE = historicoEquipoEstadoService.listHistoricoEAll();
		histRE = historicoRaspberryEstadoService.listHistoricoRAll();
	}
	
	public List<HistoricoEquipoEstado> getHistEE() {
		return histEE;
	}
	public void setHistEE(List<HistoricoEquipoEstado> histEE) {
		this.histEE = histEE;
	}
	public List<HistoricoRaspberryEstado> getHistRE() {
		return histRE;
	}
	public void setHistRE(List<HistoricoRaspberryEstado> histRE) {
		this.histRE = histRE;
	}

}
