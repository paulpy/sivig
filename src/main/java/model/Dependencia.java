package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dependencias database table.
 * 
 */
@Entity
@Table(name="dependencias")
@NamedQuery(name="Dependencia.findAll", query="SELECT d FROM Dependencia d")
public class Dependencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="depe_id_dependencia")
	private Integer depeIdDependencia;

	@Column(name="depe_estado")
	private Boolean depeEstado;

	@Column(name="depe_nombre_dependencia")
	private String depeNombreDependencia;

	//bi-directional many-to-one association to Direccione
	@ManyToOne
	@JoinColumn(name="depe_id_direccion")
	private Direccion direccion;

	//bi-directional many-to-one association to Entidade
	@ManyToOne
	@JoinColumn(name="depe_id_entidad")
	private Entidad entidad;

	//bi-directional many-to-one association to Instalacione
	@OneToMany(mappedBy="dependencia")
	private List<Instalacion> instalacions;

	//bi-directional many-to-one association to Mantenimiento
	@OneToMany(mappedBy="dependencia")
	private List<Mantenimiento> mantenimientos;

	public Dependencia() {
	}

	public Integer getDepeIdDependencia() {
		return this.depeIdDependencia;
	}

	public void setDepeIdDependencia(Integer depeIdDependencia) {
		this.depeIdDependencia = depeIdDependencia;
	}

	public Boolean getDepeEstado() {
		return this.depeEstado;
	}

	public void setDepeEstado(Boolean depeEstado) {
		this.depeEstado = depeEstado;
	}

	public String getDepeNombreDependencia() {
		return this.depeNombreDependencia;
	}

	public void setDepeNombreDependencia(String depeNombreDependencia) {
		this.depeNombreDependencia = depeNombreDependencia;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public List<Instalacion> getInstalaciones() {
		return this.instalacions;
	}

	public void setInstalaciones(List<Instalacion> instalacions) {
		this.instalacions = instalacions;
	}

	public Instalacion addInstalacione(Instalacion instalacion) {
		getInstalaciones().add(instalacion);
		instalacion.setDependencia(this);

		return instalacion;
	}

	public Instalacion removeInstalacione(Instalacion instalacion) {
		getInstalaciones().remove(instalacion);
		instalacion.setDependencia(null);

		return instalacion;
	}

	public List<Mantenimiento> getMantenimientos() {
		return this.mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public Mantenimiento addMantenimiento(Mantenimiento mantenimiento) {
		getMantenimientos().add(mantenimiento);
		mantenimiento.setDependencia(this);

		return mantenimiento;
	}

	public Mantenimiento removeMantenimiento(Mantenimiento mantenimiento) {
		getMantenimientos().remove(mantenimiento);
		mantenimiento.setDependencia(null);

		return mantenimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depeIdDependencia == null) ? 0 : depeIdDependencia.hashCode());
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
		Dependencia other = (Dependencia) obj;
		if (depeIdDependencia == null) {
			if (other.depeIdDependencia != null)
				return false;
		} else if (!depeIdDependencia.equals(other.depeIdDependencia))
			return false;
		return true;
	}

}