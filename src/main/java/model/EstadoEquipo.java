package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados_equipos database table.
 * 
 */
@Entity
@Table(name="estados_equipos")
@NamedQuery(name="EstadoEquipo.findAll", query="SELECT e FROM EstadoEquipo e")
public class EstadoEquipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="eseq_id_estado_equipo")
	private Integer eseqIdEstadoEquipo;

	@Column(name="eseq_estado_equipo")
	private String eseqEstadoEquipo;

	//bi-directional many-to-one association to HistoricoEquiposEstado
	@OneToMany(mappedBy="estadoEquipo")
	private List<HistoricoEquipoEstado> historicoEquipoEstados;

	//bi-directional many-to-one association to HistoricoRaspberrysEstado
	@OneToMany(mappedBy="estadoEquipo")
	private List<HistoricoRaspberryEstado> historicoRaspberryEstados;

	public EstadoEquipo() {
	}

	public Integer getEseqIdEstadoEquipo() {
		return this.eseqIdEstadoEquipo;
	}

	public void setEseqIdEstadoEquipo(Integer eseqIdEstadoEquipo) {
		this.eseqIdEstadoEquipo = eseqIdEstadoEquipo;
	}

	public String getEseqEstadoEquipo() {
		return this.eseqEstadoEquipo;
	}

	public void setEseqEstadoEquipo(String eseqEstadoEquipo) {
		this.eseqEstadoEquipo = eseqEstadoEquipo;
	}

	public List<HistoricoEquipoEstado> getHistoricoEquiposEstados() {
		return this.historicoEquipoEstados;
	}

	public void setHistoricoEquiposEstados(List<HistoricoEquipoEstado> historicoEquipoEstados) {
		this.historicoEquipoEstados = historicoEquipoEstados;
	}

	public HistoricoEquipoEstado addHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().add(historicoEquipoEstado);
		historicoEquipoEstado.setEstadosEquipo(this);

		return historicoEquipoEstado;
	}

	public HistoricoEquipoEstado removeHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().remove(historicoEquipoEstado);
		historicoEquipoEstado.setEstadosEquipo(null);

		return historicoEquipoEstado;
	}

	public List<HistoricoRaspberryEstado> getHistoricoRaspberrysEstados() {
		return this.historicoRaspberryEstados;
	}

	public void setHistoricoRaspberrysEstados(List<HistoricoRaspberryEstado> historicoRaspberryEstados) {
		this.historicoRaspberryEstados = historicoRaspberryEstados;
	}

	public HistoricoRaspberryEstado addHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().add(historicoRaspberryEstado);
		historicoRaspberryEstado.setEstadosEquipo(this);

		return historicoRaspberryEstado;
	}

	public HistoricoRaspberryEstado removeHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().remove(historicoRaspberryEstado);
		historicoRaspberryEstado.setEstadosEquipo(null);

		return historicoRaspberryEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eseqIdEstadoEquipo == null) ? 0 : eseqIdEstadoEquipo.hashCode());
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
		EstadoEquipo other = (EstadoEquipo) obj;
		if (eseqIdEstadoEquipo == null) {
			if (other.eseqIdEstadoEquipo != null)
				return false;
		} else if (!eseqIdEstadoEquipo.equals(other.eseqIdEstadoEquipo))
			return false;
		return true;
	}

}