package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the historico_raspberrys_estados database table.
 * 
 */
@Entity
@Table(name="historico_raspberrys_estados")
@NamedQuery(name="HistoricoRaspberryEstado.findAll", query="SELECT h FROM HistoricoRaspberryEstado h")
public class HistoricoRaspberryEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="raes_id_raspberry_estado")
	private Integer raesIdRaspberryEstado;

	@Column(name="raes_momento_cambio")
	private Timestamp raesMomentoCambio;
	
	@Column(name="raes_id_externo")
	private Integer raesIdExterno;

	//bi-directional many-to-one association to CausaCambiosEstado
	@ManyToOne
	@JoinColumn(name="raes_id_causa_cambio")
	private CausaCambioEstado causaCambioEstado;

	//bi-directional many-to-one association to EstadosEquipo
	@ManyToOne
	@JoinColumn(name="raes_id_estado_equipo")
	private EstadoEquipo estadoEquipo;

	//bi-directional many-to-one association to Raspberry
	@ManyToOne
	@JoinColumn(name="raes_id_raspberry")
	private Raspberry raspberry;

	public HistoricoRaspberryEstado() {
	}

	public Integer getRaesIdRaspberryEstado() {
		return this.raesIdRaspberryEstado;
	}

	public void setRaesIdRaspberryEstado(Integer raesIdRaspberryEstado) {
		this.raesIdRaspberryEstado = raesIdRaspberryEstado;
	}

	public Timestamp getRaesMomentoCambio() {
		return this.raesMomentoCambio;
	}

	public void setRaesMomentoCambio(Timestamp raesMomentoCambio) {
		this.raesMomentoCambio = raesMomentoCambio;
	}

	public CausaCambioEstado getCausaCambiosEstado() {
		return this.causaCambioEstado;
	}

	public void setCausaCambiosEstado(CausaCambioEstado causaCambioEstado) {
		this.causaCambioEstado = causaCambioEstado;
	}

	public EstadoEquipo getEstadosEquipo() {
		return this.estadoEquipo;
	}

	public void setEstadosEquipo(EstadoEquipo estadoEquipo) {
		this.estadoEquipo = estadoEquipo;
	}

	public Raspberry getRaspberry() {
		return this.raspberry;
	}

	public void setRaspberry(Raspberry raspberry) {
		this.raspberry = raspberry;
	}

	public Integer getRaesIdExterno() {
		return raesIdExterno;
	}

	public void setRaesIdExterno(Integer raesIdExterno) {
		this.raesIdExterno = raesIdExterno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((raesIdRaspberryEstado == null) ? 0 : raesIdRaspberryEstado.hashCode());
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
		HistoricoRaspberryEstado other = (HistoricoRaspberryEstado) obj;
		if (raesIdRaspberryEstado == null) {
			if (other.raesIdRaspberryEstado != null)
				return false;
		} else if (!raesIdRaspberryEstado.equals(other.raesIdRaspberryEstado))
			return false;
		return true;
	}

}