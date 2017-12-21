package managedBean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.solder.servlet.http.RequestParam;
import org.primefaces.context.RequestContext;

import model.Accion;
import model.Equipo;
import model.HistoricoEquipoEstado;
import model.HistoricoRaspberryEstado;
import model.Interaccion;
import service.AccionService;
import service.EquipoService;
import service.HistoricoEquipoEstadoService;
import service.HistoricoRaspberryEstadoService;
import service.InteraccionService;
import service.UsuarioService;

@ViewScoped
@ManagedBean
public class InteraccionMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Interaccion nuevaInteraccion;
	private Equipo nuevoEquipo;
	private Accion accionRealizada;
	private Interaccion ultimaInteraccion;
	Integer ultimoId=0;
	
	@Inject
	private FacesContext context;
	@Inject
	private InteraccionService interaccionService;
	@Inject
	private EquipoService equipoService;
	@Inject
	private HistoricoEquipoEstadoService historicoEquipoEstadoService;
	@Inject
	private HistoricoRaspberryEstadoService historicoRaspberryEstadoService;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private AccionService accionService;
	@RequestParam
	private String idEquipoParam;
	@RequestParam
	private String usuarioParam;
	
	private List<HistoricoEquipoEstado> historicoEquipoList;
	private List<HistoricoRaspberryEstado> historicoRaspberryList;
	private List<Interaccion> interaccionList;
	
	public void inicializarPanelControl(){
		nuevoEquipo = new Equipo();
		Integer id = Integer.parseInt(idEquipoParam);
		nuevoEquipo = equipoService.getEquipo(id);
		listarHistoricos(nuevoEquipo);
		limpiar();
	}
	
	public void listarHistoricos(Equipo nuevoEquipo){
		historicoEquipoList = historicoEquipoEstadoService.listHistoricoE(nuevoEquipo.getEquiIdEquipo());
		historicoRaspberryList = historicoRaspberryEstadoService.listHistoricoR(nuevoEquipo.getRaspberry().getRaspIdRaspberry());
		interaccionList = interaccionService.listInteraccionesLog(nuevoEquipo.getRaspberry());
	}
	
	public void apagarR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(1));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(10);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Apagar"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void reiniciarR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(2));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(20);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Reiniciar"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void sincHoraR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setInteComando(timestamp.toString());
		nuevaInteraccion.setAccion(accionService.getAccion(4));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(40);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Sincronizacion de Hora"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void sincEstadosR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(5));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(50);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Sincronizar Estados"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void sincAccionesR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(7));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(70);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Sincronizar Acciones"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void sincCausasR(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(8));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(80);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Sincronizar Causas"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void apagadoE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(1));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(11);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Apagar Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void encendidoE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(3));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(31);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Encender Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void reinicioE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(2));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(21);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Reinicio Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void sincHoraE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setInteComando(timestamp.toString());
		nuevaInteraccion.setAccion(accionService.getAccion(4));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(41);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Sincronizar Hora Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void crearBackupE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(6));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(61);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Crear Backup Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void inicioSE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(9));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(91);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Iniciar Servicio Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void reinicioSE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(10));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(101);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Reiniciar Servicio Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void paradaSE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(11));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(111);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Parar Servicio Equipo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void enviarComandoE(String usuariodata){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		nuevaInteraccion.setEquipo(nuevoEquipo);
		nuevaInteraccion.setRaspberry(nuevoEquipo.getRaspberry());
		nuevaInteraccion.setUsuario(usuarioService.getUsuario(usuariodata));
		nuevaInteraccion.setInteFechaHoraSolicitud(timestamp);
		nuevaInteraccion.setAccion(accionService.getAccion(12));
		nuevaInteraccion.setInteActivo(true);
		nuevaInteraccion.setInteCodigo(121);
		try {
			interaccionService.registrarInteraccion(nuevaInteraccion);
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Guardado", "Interaccion Enviar Comando"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
	}
	
	public void verificarEquipo(){
		listarHistoricos(nuevoEquipo);
		RequestContext.getCurrentInstance().update("mainForm:historicoEquiposTabla");
		RequestContext.getCurrentInstance().update("mainForm:historicoRaspberryTabla");
		RequestContext.getCurrentInstance().update("mainForm:estadoequipooutput");
		RequestContext.getCurrentInstance().update("mainForm:interaccionestabla");
		ultimaInteraccion = interaccionService.ultimaInteraccion(nuevoEquipo.getEquiIdEquipo());
		String mensaje = null;
		System.out.println(ultimaInteraccion.getInteIdInteraccion());
		System.out.println(ultimoId);
		if (ultimoId.equals(ultimaInteraccion.getInteIdInteraccion())) {
			System.out.println("Interaccion ya leida");
		} else{
			System.out.println(ultimaInteraccion.getInteResultado());
			if(ultimaInteraccion.getInteResultado().equals("0")){
				mensaje = "Completado";
			} else {
				mensaje = "Interaccion no Completada";
			}
			context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Resultado de la Ultima Interaccion", mensaje));
		}
		ultimoId=ultimaInteraccion.getInteIdInteraccion();
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
	
	public String colorInteraccion(boolean activo){
		String colorInteraccion = null;
		if(activo){
			colorInteraccion = "Orange";
		} else {
			colorInteraccion = "Blue";
		}
		return colorInteraccion;
	}
	
	public String estadoInteraccion(boolean activo){
		String estadoInteraccion = null;
		if(activo){
			estadoInteraccion = "En Espera";
		} else {
			estadoInteraccion = "Cumplido";
		}
		return estadoInteraccion;
	}
	
	public void limpiar(){
		nuevaInteraccion = new Interaccion();
	}

	public Interaccion getNuevaInteraccion() {
		return nuevaInteraccion;
	}

	public void setNuevaInteraccion(Interaccion nuevaInteraccion) {
		this.nuevaInteraccion = nuevaInteraccion;
	}

	public Equipo getNuevoEquipo() {
		return nuevoEquipo;
	}

	public void setNuevoEquipo(Equipo nuevoEquipo) {
		this.nuevoEquipo = nuevoEquipo;
	}

	public String getIdEquipoParam() {
		return idEquipoParam;
	}

	public void setIdEquipoParam(String idEquipoParam) {
		this.idEquipoParam = idEquipoParam;
	}

	public List<HistoricoEquipoEstado> getHistoricoEquipoList() {
		return historicoEquipoList;
	}

	public void setHistoricoEquipoList(List<HistoricoEquipoEstado> historicoEquipoList) {
		this.historicoEquipoList = historicoEquipoList;
	}

	public List<HistoricoRaspberryEstado> getHistoricoRaspberryList() {
		return historicoRaspberryList;
	}

	public void setHistoricoRaspberryList(List<HistoricoRaspberryEstado> historicoRaspberryList) {
		this.historicoRaspberryList = historicoRaspberryList;
	}

	public Accion getAccionRealizada() {
		return accionRealizada;
	}

	public void setAccionRealizada(Accion accionRealizada) {
		this.accionRealizada = accionRealizada;
	}

	public String getUsuarioParam() {
		return usuarioParam;
	}

	public void setUsuarioParam(String usuarioParam) {
		this.usuarioParam = usuarioParam;
	}

	public List<Interaccion> getInteraccionList() {
		return interaccionList;
	}

	public void setInteraccionList(List<Interaccion> interaccionList) {
		this.interaccionList = interaccionList;
	}
	
	
}
