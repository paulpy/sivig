package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the piezas database table.
 * 
 */
@Entity
@Table(name="piezas")
@NamedQuery(name="Pieza.findAll", query="SELECT p FROM Pieza p")
public class Pieza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="piez_id_pieza")
	private Integer piezIdPieza;

	@Column(name="piez_cantidad")
	private Integer piezCantidad;

	@Column(name="piez_detalle")
	private String piezDetalle;

	@Column(name="piez_pieza")
	private String piezPieza;
	
	@Temporal(TemporalType.DATE)
	@Column(name="piez_ultima_actualizacion")
	private Date piezUltimaActualizacion;

	//bi-directional many-to-one association to CambiosPieza
	@OneToMany(mappedBy="pieza")
	private List<CambioPieza> cambioPiezas;

	public Pieza() {
	}

	public Integer getPiezIdPieza() {
		return this.piezIdPieza;
	}

	public void setPiezIdPieza(Integer piezIdPieza) {
		this.piezIdPieza = piezIdPieza;
	}

	public Integer getPiezCantidad() {
		return this.piezCantidad;
	}

	public void setPiezCantidad(Integer piezCantidad) {
		this.piezCantidad = piezCantidad;
	}

	public String getPiezDetalle() {
		return this.piezDetalle;
	}

	public void setPiezDetalle(String piezDetalle) {
		this.piezDetalle = piezDetalle;
	}

	public String getPiezPieza() {
		return this.piezPieza;
	}

	public void setPiezPieza(String piezPieza) {
		this.piezPieza = piezPieza;
	}

	public List<CambioPieza> getCambiosPiezas() {
		return this.cambioPiezas;
	}

	public void setCambiosPiezas(List<CambioPieza> cambioPiezas) {
		this.cambioPiezas = cambioPiezas;
	}

	public CambioPieza addCambiosPieza(CambioPieza cambioPieza) {
		getCambiosPiezas().add(cambioPieza);
		cambioPieza.setPieza(this);

		return cambioPieza;
	}

	public CambioPieza removeCambiosPieza(CambioPieza cambioPieza) {
		getCambiosPiezas().remove(cambioPieza);
		cambioPieza.setPieza(null);

		return cambioPieza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((piezIdPieza == null) ? 0 : piezIdPieza.hashCode());
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
		Pieza other = (Pieza) obj;
		if (piezIdPieza == null) {
			if (other.piezIdPieza != null)
				return false;
		} else if (!piezIdPieza.equals(other.piezIdPieza))
			return false;
		return true;
	}

}