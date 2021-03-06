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

import org.primefaces.model.StreamedContent;

import clases.AuditoriaClass;
import model.Entidad;
import model.Funcionario;
import reporte.GeneradorDeReporte;
import service.EntidadService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class ReportesSistemaMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionarioService funcionarioService;
	@Inject
	private EntidadService entidadService;
	@Inject
	private FacesContext context;
	@Inject
	private GeneradorDeReporte generadorDeReporte;
	@Inject
	private AuditoriaClass auditoriaClass;

	private List<Entidad> entidadList;
	private List<Funcionario> funcionarioTecList;
	private Date fechaInicio;
	private Date fechaFin;
	private Funcionario funcionarioSelect;
	private Map<String, Object> parametros;
	private String tipoReporte;
	private String urlreporte;
	private Entidad entidadSelect;

	public void inicializar() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (tipoReporte != null) {
				System.out.println("ya tiene contexto " + tipoReporte);
			} else {
				String contexto = FacesContext.getCurrentInstance().getExternalContext().getRequest().toString()
						.substring(63, 67);
				tipoReporte = contexto;
			}
		}
		if (tipoReporte.equals("rins")) {
			entidadList = entidadService.listEntidad();
			System.out.println("entro a instalaciones");
		}
		if (tipoReporte.equals("rcdp")) {
			funcionarioTecList = funcionarioService.listFuncionarioTec();
			System.out.println("entro a cambio de pieza");
		}
		if (tipoReporte.equals("rtlc")) {
			System.out.println("entro a contratos");
		}
		if (tipoReporte.equals("rtlp")) {
			System.out.println("entro a piezas");
		}
		if (tipoReporte.equals("rman")) {
			entidadList = entidadService.listEntidad();
			System.out.println("entro a manteminietos");
		}
		if (tipoReporte.equals("/gen")) {
			entidadList = entidadService.listEntidad();
			System.out.println("entro a auditoria");
		}
	}

	public StreamedContent generarReporteAll(String usuario) {
		if (fechaInicio == null) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Falta Ingresar Fecha Inicio");
			context.addMessage(null, m);
		} else {
			if (fechaFin == null) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Falta Ingresar Fecha Fin");
				context.addMessage(null, m);
			} else {
				if ((fechaInicio.after(fechaFin)) || (fechaFin.before(fechaInicio))) {
					System.out.println("Las fechas no estan en el orden Correcto");
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atencion",
							"Las fechas no estan en el orden Correcto");
					context.addMessage(null, m);
				} else {
					parametros = new HashMap<String, Object>();
					parametros.put("fechainicio", fechaInicio);
					parametros.put("fechafin", fechaFin);
					if (tipoReporte.equals("rtlc")) {
						FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
								"Se generara el Reporte de Contratos");
						context.addMessage(null, m);
						urlreporte = "/reportes/todosloscontratos.jrxml";
						auditoriaClass.agregarAuditoria("Generando Reporte de Contratos", "vistareporte", usuario);
					}
					if (tipoReporte.equals("rtlp")) {
						FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
								"Se generara el Reporte de Piezas");
						context.addMessage(null, m);
						urlreporte = "/reportes/todaslaspiezas.jrxml";
						auditoriaClass.agregarAuditoria("Generando Reporte de Contratos", "vistareporte", usuario);
					}
					if (tipoReporte.equals("/gen")) {
						FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
								"Se generara el Reporte de Auditoria");
						context.addMessage(null, m);
						urlreporte = "/reportes/auditoriafechas.jrxml";
						auditoriaClass.agregarAuditoria("Generando Reporte de Auditoria", "Auditoria", usuario);
					}
					if (tipoReporte.equals("rcdp")) {
						if (funcionarioSelect == null) {
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte sin filtro de Funcionario");
							context.addMessage(null, m);
							urlreporte = "/reportes/cambiodepiezasmes.jrxml";
						} else {
							parametros.put("id", funcionarioSelect.getPersona().getPersIdPersona());
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte con Funcionario");
							context.addMessage(null, m);
							urlreporte = "/reportes/cambiodepiezasmesfunc.jrxml";
						}
						auditoriaClass.agregarAuditoria("Generando Reporte Cambio de Pieza", "vistareporte", usuario);
					}
					if (tipoReporte.equals("rins")) {
						if (entidadSelect == null) {
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte sin filtro de Entidad");
							context.addMessage(null, m);
							urlreporte = "/reportes/instalacionespormes.jrxml";
						} else {
							parametros.put("id", entidadSelect.getEntiIdEntidad());
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte con Entidad");
							context.addMessage(null, m);
							urlreporte = "/reportes/instalacionespormesporentidad.jrxml";
						}
						auditoriaClass.agregarAuditoria("Generando Reporte de Instalaciones", "vistareporte", usuario);
					}
					if (tipoReporte.equals("rman")) {
						if (entidadSelect == null) {
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte sin filtro de Entidad");
							context.addMessage(null, m);
							urlreporte = "/reportes/mantenimientodelmes.jrxml";
						} else {
							parametros.put("id", entidadSelect.getEntiIdEntidad());
							FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion",
									"Se generara el Reporte con Entidad");
							context.addMessage(null, m);
							urlreporte = "/reportes/mantenimientodelmesentidad.jrxml";
						}
						auditoriaClass.agregarAuditoria("Generando Reporte de Mantenimiento", "vistareporte", usuario);
					}
					try {
						return generadorDeReporte.generarReporte(urlreporte, parametros);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.toString());
					}
				}
			}
		}
		return null;
	}

	public void listar() {
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

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List<Entidad> getEntidadList() {
		return entidadList;
	}

	public void setEntidadList(List<Entidad> entidadList) {
		this.entidadList = entidadList;
	}

	public Entidad getEntidadSelect() {
		return entidadSelect;
	}

	public void setEntidadSelect(Entidad entidadSelect) {
		this.entidadSelect = entidadSelect;
	}

}
