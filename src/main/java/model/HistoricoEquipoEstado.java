package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the historico_equipos_estados database table.
 * 
 */
@Entity
@Table(name="historico_equipos_estados")
@NamedQuery(name="HistoricoEquipoEstado.findAll", query="SELECT h FROM HistoricoEquipoEstado h")
public class HistoricoEquipoEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="eqes_id_historico_estado_reloj")
	private Integer eqesIdHistoricoEstadoReloj;

	@Column(name="eqes_momento_estado")
	private Timestamp eqesMomentoEstado;
	
	@Column(name="eqes_id_externo")
	private Integer eqesIdExterno;

	//bi-directional many-to-one association to CausaCambiosEstado
	@ManyToOne
	@JoinColumn(name="eqes_id_causa_cambio")
	private CausaCambioEstado causaCambioEstado;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="eqes_id_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to EstadosEquipo
	@ManyToOne
	@JoinColumn(name="eqes_id_estado_equipo")
	private EstadoEquipo estadoEquipo;

	public HistoricoEquipoEstado() {
	}

	public Integer getEqesIdHistoricoEstadoReloj() {
		return this.eqesIdHistoricoEstadoReloj;
	}

	public void setEqesIdHistoricoEstadoReloj(Integer eqesIdHistoricoEstadoReloj) {
		this.eqesIdHistoricoEstadoReloj = eqesIdHistoricoEstadoReloj;
	}

	public Timestamp getEqesMomentoEstado() {
		return this.eqesMomentoEstado;
	}

	public void setEqesMomentoEstado(Timestamp eqesMomentoEstado) {
		this.eqesMomentoEstado = eqesMomentoEstado;
	}

	public CausaCambioEstado getCausaCambiosEstado() {
		return this.causaCambioEstado;
	}

	public void setCausaCambiosEstado(CausaCambioEstado causaCambioEstado) {
		this.causaCambioEstado = causaCambioEstado;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public EstadoEquipo getEstadosEquipo() {
		return this.estadoEquipo;
	}

	public void setEstadosEquipo(EstadoEquipo estadoEquipo) {
		this.estadoEquipo = estadoEquipo;
	}
	
	public Integer getEqesIdExterno() {
		return eqesIdExterno;
	}

	public void setEqesIdExterno(Integer eqesIdExterno) {
		this.eqesIdExterno = eqesIdExterno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eqesIdHistoricoEstadoReloj == null) ? 0 : eqesIdHistoricoEstadoReloj.hashCode());
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
		HistoricoEquipoEstado other = (HistoricoEquipoEstado) obj;
		if (eqesIdHistoricoEstadoReloj == null) {
			if (other.eqesIdHistoricoEstadoReloj != null)
				return false;
		} else if (!eqesIdHistoricoEstadoReloj.equals(other.eqesIdHistoricoEstadoReloj))
			return false;
		return true;
	}

}