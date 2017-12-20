package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the raspberrys database table.
 * 
 */
@Entity
@Table(name="raspberrys")
@NamedQuery(name="Raspberry.findAll", query="SELECT r FROM Raspberry r")
public class Raspberry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rasp_id_raspberry")
	private Integer raspIdRaspberry;

	@Column(name="rasp_activo")
	private Boolean raspActivo;

	@Temporal(TemporalType.DATE)
	@Column(name="rasp_fecha_ingreso")
	private Date raspFechaIngreso;

	@Column(name="rasp_ip")
	private String raspIp;

	@Column(name="rasp_nombre")
	private String raspNombre;
	
	@Column(name="rasp_estado")
	private String raspEstado;
	
	@Column(name="rasp_ultima_comunicacion")
	private Timestamp raspUltimaComunicacion;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="raspberry")
	private List<Equipo> equipos;

	//bi-directional many-to-one association to HistoricoRaspberrysEstado
	@OneToMany(mappedBy="raspberry")
	private List<HistoricoRaspberryEstado> historicoRaspberryEstados;
	
	//bi-directional many-to-one association to Interaccion
	@OneToMany(mappedBy="raspberry")
	private List<Interaccion> interacciones;

	public Raspberry() {
	}

	public Integer getRaspIdRaspberry() {
		return this.raspIdRaspberry;
	}

	public void setRaspIdRaspberry(Integer raspIdRaspberry) {
		this.raspIdRaspberry = raspIdRaspberry;
	}

	public Boolean getRaspActivo() {
		return this.raspActivo;
	}

	public void setRaspActivo(Boolean raspActivo) {
		this.raspActivo = raspActivo;
	}

	public Date getRaspFechaIngreso() {
		return this.raspFechaIngreso;
	}

	public void setRaspFechaIngreso(Date raspFechaIngreso) {
		this.raspFechaIngreso = raspFechaIngreso;
	}

	public String getRaspIp() {
		return this.raspIp;
	}

	public void setRaspIp(String raspIp) {
		this.raspIp = raspIp;
	}

	public String getRaspNombre() {
		return this.raspNombre;
	}

	public void setRaspNombre(String raspNombre) {
		this.raspNombre = raspNombre;
	}
	
	public String getRaspEstado() {
		return raspEstado;
	}

	public void setRaspEstado(String raspEstado) {
		this.raspEstado = raspEstado;
	}

	public Timestamp getRaspUltimaComunicacion() {
		return raspUltimaComunicacion;
	}

	public void setRaspUltimaComunicacion(Timestamp raspUltimaComunicacion) {
		this.raspUltimaComunicacion = raspUltimaComunicacion;
	}

	public List<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setRaspberry(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setRaspberry(null);

		return equipo;
	}

	public List<HistoricoRaspberryEstado> getHistoricoRaspberrysEstados() {
		return this.historicoRaspberryEstados;
	}

	public void setHistoricoRaspberrysEstados(List<HistoricoRaspberryEstado> historicoRaspberryEstados) {
		this.historicoRaspberryEstados = historicoRaspberryEstados;
	}

	public HistoricoRaspberryEstado addHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().add(historicoRaspberryEstado);
		historicoRaspberryEstado.setRaspberry(this);

		return historicoRaspberryEstado;
	}

	public HistoricoRaspberryEstado removeHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().remove(historicoRaspberryEstado);
		historicoRaspberryEstado.setRaspberry(null);

		return historicoRaspberryEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((raspIp == null) ? 0 : raspIp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raspberry other = (Raspberry) obj;
		if (raspIp == null) {
			if (other.raspIp != null)
				return false;
		} else if (!raspIp.equals(other.raspIp))
			return false;
		return true;
	}

}