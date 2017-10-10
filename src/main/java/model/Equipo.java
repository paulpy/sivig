package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="equi_id_equipo")
	private Integer equiIdEquipo;

	@Column(name="equi_activo")
	private Boolean equiActivo;

	@Temporal(TemporalType.DATE)
	@Column(name="equi_fecha_ingreso")
	private Date equiFechaIngreso;

	@Column(name="equi_serie_equipo")
	private String equiSerieEquipo;
	
	@Column(name="equi_identificador")
	private String equiIdentificador;
	
	@Column(name="equi_estado")
	private String equiEstado;

	//bi-directional many-to-one association to CambiosPieza
	@OneToMany(mappedBy="equipo")
	private List<CambioPieza> cambioPiezas;

	//bi-directional many-to-one association to Raspberry
	@ManyToOne
	@JoinColumn(name="equi_id_raspberry")
	private Raspberry raspberry;

	//bi-directional many-to-one association to HistoricoEquiposEstado
	@OneToMany(mappedBy="equipo")
	private List<HistoricoEquipoEstado> historicoEquipoEstados;

	//bi-directional many-to-one association to Instalacione
	@OneToMany(mappedBy="equipo")
	private List<Instalacion> instalacions;
	
	//bi-directional many-to-one association to Interaccion
	@OneToMany(mappedBy="equipo")
	private List<Interaccion> interacciones;

	public Equipo() {
	}

	public Integer getEquiIdEquipo() {
		return this.equiIdEquipo;
	}

	public void setEquiIdEquipo(Integer equiIdEquipo) {
		this.equiIdEquipo = equiIdEquipo;
	}

	public Boolean getEquiActivo() {
		return this.equiActivo;
	}

	public void setEquiActivo(Boolean equiActivo) {
		this.equiActivo = equiActivo;
	}

	public Date getEquiFechaIngreso() {
		return this.equiFechaIngreso;
	}

	public void setEquiFechaIngreso(Date equiFechaIngreso) {
		this.equiFechaIngreso = equiFechaIngreso;
	}

	public String getEquiSerieEquipo() {
		return this.equiSerieEquipo;
	}

	public void setEquiSerieEquipo(String equiSerieEquipo) {
		this.equiSerieEquipo = equiSerieEquipo;
	}

	public String getEquiEstado() {
		return equiEstado;
	}

	public void setEquiEstado(String equiEstado) {
		this.equiEstado = equiEstado;
	}

	public String getEquiIdenificador() {
		return equiIdentificador;
	}

	public void setEquiIdenificador(String equiIdentificador) {
		this.equiIdentificador = equiIdentificador;
	}

	public List<CambioPieza> getCambiosPiezas() {
		return this.cambioPiezas;
	}

	public void setCambiosPiezas(List<CambioPieza> cambioPiezas) {
		this.cambioPiezas = cambioPiezas;
	}

	public CambioPieza addCambiosPieza(CambioPieza cambioPieza) {
		getCambiosPiezas().add(cambioPieza);
		cambioPieza.setEquipo(this);

		return cambioPieza;
	}

	public CambioPieza removeCambiosPieza(CambioPieza cambioPieza) {
		getCambiosPiezas().remove(cambioPieza);
		cambioPieza.setEquipo(null);

		return cambioPieza;
	}

	public Raspberry getRaspberry() {
		return this.raspberry;
	}

	public void setRaspberry(Raspberry raspberry) {
		this.raspberry = raspberry;
	}

	public List<HistoricoEquipoEstado> getHistoricoEquiposEstados() {
		return this.historicoEquipoEstados;
	}

	public void setHistoricoEquiposEstados(List<HistoricoEquipoEstado> historicoEquipoEstados) {
		this.historicoEquipoEstados = historicoEquipoEstados;
	}

	public HistoricoEquipoEstado addHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().add(historicoEquipoEstado);
		historicoEquipoEstado.setEquipo(this);

		return historicoEquipoEstado;
	}

	public HistoricoEquipoEstado removeHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().remove(historicoEquipoEstado);
		historicoEquipoEstado.setEquipo(null);

		return historicoEquipoEstado;
	}

	public List<Instalacion> getInstalaciones() {
		return this.instalacions;
	}

	public void setInstalaciones(List<Instalacion> instalacions) {
		this.instalacions = instalacions;
	}

	public Instalacion addInstalacione(Instalacion instalacion) {
		getInstalaciones().add(instalacion);
		instalacion.setEquipo(this);

		return instalacion;
	}

	public Instalacion removeInstalacione(Instalacion instalacion) {
		getInstalaciones().remove(instalacion);
		instalacion.setEquipo(null);

		return instalacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equiIdEquipo == null) ? 0 : equiIdEquipo.hashCode());
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
		Equipo other = (Equipo) obj;
		if (equiIdEquipo == null) {
			if (other.equiIdEquipo != null)
				return false;
		} else if (!equiIdEquipo.equals(other.equiIdEquipo))
			return false;
		return true;
	}

}