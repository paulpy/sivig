package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the direcciones database table.
 * 
 */
@Entity
@Table(name="direcciones")
@NamedQuery(name="Direccion.findAll", query="SELECT d FROM Direccion d")
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dire_id_direccion")
	private Integer direIdDireccion;

	@Column(name="dire_nro")
	private Integer direNro;

	//bi-directional many-to-one association to Dependencia
	@OneToMany(mappedBy="direccion")
	private List<Dependencia> dependencias;

	//bi-directional many-to-one association to Calle
	@ManyToOne
	@JoinColumn(name="dire_id_calle_1")
	private Calle calle1;

	//bi-directional many-to-one association to Calle
	@ManyToOne
	@JoinColumn(name="dire_id_calle_3")
	private Calle calle2;

	//bi-directional many-to-one association to Calle
	@ManyToOne
	@JoinColumn(name="dire_id_calle_2")
	private Calle calle3;

	//bi-directional many-to-one association to Ciudade
	@ManyToOne
	@JoinColumn(name="dire_id_ciudad")
	private Ciudad ciudad;

	public Direccion() {
	}

	public Integer getDireIdDireccion() {
		return this.direIdDireccion;
	}

	public void setDireIdDireccion(Integer direIdDireccion) {
		this.direIdDireccion = direIdDireccion;
	}

	public Integer getDireNro() {
		return this.direNro;
	}

	public void setDireNro(Integer direNro) {
		this.direNro = direNro;
	}

	public List<Dependencia> getDependencias() {
		return this.dependencias;
	}

	public void setDependencias(List<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}

	public Dependencia addDependencia(Dependencia dependencia) {
		getDependencias().add(dependencia);
		dependencia.setDireccion(this);

		return dependencia;
	}

	public Dependencia removeDependencia(Dependencia dependencia) {
		getDependencias().remove(dependencia);
		dependencia.setDireccion(null);

		return dependencia;
	}

	public Calle getCalle1() {
		return this.calle1;
	}

	public void setCalle1(Calle calle1) {
		this.calle1 = calle1;
	}

	public Calle getCalle2() {
		return this.calle2;
	}

	public void setCalle2(Calle calle2) {
		this.calle2 = calle2;
	}

	public Calle getCalle3() {
		return this.calle3;
	}

	public void setCalle3(Calle calle3) {
		this.calle3 = calle3;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direIdDireccion == null) ? 0 : direIdDireccion.hashCode());
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
		Direccion other = (Direccion) obj;
		if (direIdDireccion == null) {
			if (other.direIdDireccion != null)
				return false;
		} else if (!direIdDireccion.equals(other.direIdDireccion))
			return false;
		return true;
	}

}