package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the calles database table.
 * 
 */
@Entity
@Table(name="calles")
@NamedQuery(name="Calle.findAll", query="SELECT c FROM Calle c")
public class Calle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="call_id_calle")
	private Integer callIdCalle;

	@Column(name="call_calle")
	private String callCalle;

	//bi-directional many-to-one association to Direccione
	@OneToMany(mappedBy="calle1")
	private List<Direccion> direcciones1;

	//bi-directional many-to-one association to Direccione
	@OneToMany(mappedBy="calle2")
	private List<Direccion> direcciones2;

	//bi-directional many-to-one association to Direccione
	@OneToMany(mappedBy="calle3")
	private List<Direccion> direcciones3;

	public Calle() {
	}

	public Integer getCallIdCalle() {
		return this.callIdCalle;
	}

	public void setCallIdCalle(Integer callIdCalle) {
		this.callIdCalle = callIdCalle;
	}

	public String getCallCalle() {
		return this.callCalle;
	}

	public void setCallCalle(String callCalle) {
		this.callCalle = callCalle;
	}

	public List<Direccion> getDirecciones1() {
		return this.direcciones1;
	}

	public void setDirecciones1(List<Direccion> direcciones1) {
		this.direcciones1 = direcciones1;
	}

	public Direccion addDirecciones1(Direccion direcciones1) {
		getDirecciones1().add(direcciones1);
		direcciones1.setCalle1(this);

		return direcciones1;
	}

	public Direccion removeDirecciones1(Direccion direcciones1) {
		getDirecciones1().remove(direcciones1);
		direcciones1.setCalle1(null);

		return direcciones1;
	}

	public List<Direccion> getDirecciones2() {
		return this.direcciones2;
	}

	public void setDirecciones2(List<Direccion> direcciones2) {
		this.direcciones2 = direcciones2;
	}

	public Direccion addDirecciones2(Direccion direcciones2) {
		getDirecciones2().add(direcciones2);
		direcciones2.setCalle2(this);

		return direcciones2;
	}

	public Direccion removeDirecciones2(Direccion direcciones2) {
		getDirecciones2().remove(direcciones2);
		direcciones2.setCalle2(null);

		return direcciones2;
	}

	public List<Direccion> getDirecciones3() {
		return this.direcciones3;
	}

	public void setDirecciones3(List<Direccion> direcciones3) {
		this.direcciones3 = direcciones3;
	}

	public Direccion addDirecciones3(Direccion direcciones3) {
		getDirecciones3().add(direcciones3);
		direcciones3.setCalle3(this);

		return direcciones3;
	}

	public Direccion removeDirecciones3(Direccion direcciones3) {
		getDirecciones3().remove(direcciones3);
		direcciones3.setCalle3(null);

		return direcciones3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callIdCalle == null) ? 0 : callIdCalle.hashCode());
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
		Calle other = (Calle) obj;
		if (callIdCalle == null) {
			if (other.callIdCalle != null)
				return false;
		} else if (!callIdCalle.equals(other.callIdCalle))
			return false;
		return true;
	}

}