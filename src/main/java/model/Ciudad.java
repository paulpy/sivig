package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ciudades database table.
 * 
 */
@Entity
@Table(name="ciudades")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ciud_id_ciudad")
	private Integer ciudIdCiudad;

	@Column(name="ciud_ciudad")
	private String ciudCiudad;

	//bi-directional many-to-one association to Direccione
	@OneToMany(mappedBy="ciudad")
	private List<Direccion> direccions;

	public Ciudad() {
	}

	public Integer getCiudIdCiudad() {
		return this.ciudIdCiudad;
	}

	public void setCiudIdCiudad(Integer ciudIdCiudad) {
		this.ciudIdCiudad = ciudIdCiudad;
	}

	public String getCiudCiudad() {
		return this.ciudCiudad;
	}

	public void setCiudCiudad(String ciudCiudad) {
		this.ciudCiudad = ciudCiudad;
	}

	public List<Direccion> getDirecciones() {
		return this.direccions;
	}

	public void setDirecciones(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccione(Direccion direccion) {
		getDirecciones().add(direccion);
		direccion.setCiudad(this);

		return direccion;
	}

	public Direccion removeDireccione(Direccion direccion) {
		getDirecciones().remove(direccion);
		direccion.setCiudad(null);

		return direccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudIdCiudad == null) ? 0 : ciudIdCiudad.hashCode());
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
		Ciudad other = (Ciudad) obj;
		if (ciudIdCiudad == null) {
			if (other.ciudIdCiudad != null)
				return false;
		} else if (!ciudIdCiudad.equals(other.ciudIdCiudad))
			return false;
		return true;
	}

}