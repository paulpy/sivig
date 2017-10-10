package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entidades database table.
 * 
 */
@Entity
@Table(name="entidades")
@NamedQuery(name="Entidad.findAll", query="SELECT e FROM Entidad e")
public class Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="enti_id_entidad")
	private Integer entiIdEntidad;

	@Column(name="enti_entidad")
	private String entiEntidad;

	@Column(name="enti_estado")
	private Boolean entiEstado;

	//bi-directional many-to-one association to Contrato
	@OneToMany(mappedBy="entidad")
	private List<Contrato> contratos;

	//bi-directional many-to-one association to Dependencia
	@OneToMany(mappedBy="entidad")
	private List<Dependencia> dependencias;

	public Entidad() {
	}

	public Integer getEntiIdEntidad() {
		return this.entiIdEntidad;
	}

	public void setEntiIdEntidad(Integer entiIdEntidad) {
		this.entiIdEntidad = entiIdEntidad;
	}

	public String getEntiEntidad() {
		return this.entiEntidad;
	}

	public void setEntiEntidad(String entiEntidad) {
		this.entiEntidad = entiEntidad;
	}

	public Boolean getEntiEstado() {
		return this.entiEstado;
	}

	public void setEntiEstado(Boolean entiEstado) {
		this.entiEstado = entiEstado;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Contrato addContrato(Contrato contrato) {
		getContratos().add(contrato);
		contrato.setEntidad(this);

		return contrato;
	}

	public Contrato removeContrato(Contrato contrato) {
		getContratos().remove(contrato);
		contrato.setEntidad(null);

		return contrato;
	}

	public List<Dependencia> getDependencias() {
		return this.dependencias;
	}

	public void setDependencias(List<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}

	public Dependencia addDependencia(Dependencia dependencia) {
		getDependencias().add(dependencia);
		dependencia.setEntidad(this);

		return dependencia;
	}

	public Dependencia removeDependencia(Dependencia dependencia) {
		getDependencias().remove(dependencia);
		dependencia.setEntidad(null);

		return dependencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entiIdEntidad == null) ? 0 : entiIdEntidad.hashCode());
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
		Entidad other = (Entidad) obj;
		if (entiIdEntidad == null) {
			if (other.entiIdEntidad != null)
				return false;
		} else if (!entiIdEntidad.equals(other.entiIdEntidad))
			return false;
		return true;
	}

}