package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the acciones database table.
 * 
 */
@Entity
@Table(name="acciones")
@NamedQuery(name="Accion.findAll", query="SELECT a FROM Accion a")
public class Accion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="acci_id_accion")
	private Integer acciIdAccion;

	@Column(name="acci_accion")
	private String acciAccion;

	//bi-directional many-to-one association to Interaccion
	@OneToMany(mappedBy="accion")
	private List<Interaccion> interacciones;

	public Accion() {
	}

	public Integer getAcciIdAccion() {
		return this.acciIdAccion;
	}

	public void setAcciIdAccion(Integer acciIdAccion) {
		this.acciIdAccion = acciIdAccion;
	}

	public String getAcciAccion() {
		return this.acciAccion;
	}

	public void setAcciAccion(String acciAccion) {
		this.acciAccion = acciAccion;
	}

	public List<Interaccion> getInteracciones() {
		return this.interacciones;
	}

	public void setInteracciones(List<Interaccion> interacciones) {
		this.interacciones = interacciones;
	}

	public Interaccion addInteraccione(Interaccion interaccion) {
		getInteracciones().add(interaccion);
		interaccion.setAccion(this);

		return interaccion;
	}

	public Interaccion removeInteraccione(Interaccion interaccion) {
		getInteracciones().remove(interaccion);
		interaccion.setAccion(null);

		return interaccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acciIdAccion == null) ? 0 : acciIdAccion.hashCode());
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
		Accion other = (Accion) obj;
		if (acciIdAccion == null) {
			if (other.acciIdAccion != null)
				return false;
		} else if (!acciIdAccion.equals(other.acciIdAccion))
			return false;
		return true;
	}
	
}