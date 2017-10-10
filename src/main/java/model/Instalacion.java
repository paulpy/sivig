package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the instalaciones database table.
 * 
 */
@Entity
@Table(name="instalaciones")
@NamedQuery(name="Instalacion.findAll", query="SELECT i FROM Instalacion i")
public class Instalacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inst_id_instalacion")
	private Integer instIdInstalacion;

	@Temporal(TemporalType.DATE)
	@Column(name="inst_fecha_instalacion")
	private Date instFechaInstalacion;

	@Column(name="inst_observacion")
	private String instObservacion;

	//bi-directional many-to-one association to Dependencia
	@ManyToOne
	@JoinColumn(name="inst_id_dependencia")
	private Dependencia dependencia;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="inst_id_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="inst_id_funcionario")
	private Funcionario funcionario;

	public Instalacion() {
	}

	public Integer getInstIdInstalacion() {
		return this.instIdInstalacion;
	}

	public void setInstIdInstalacion(Integer instIdInstalacion) {
		this.instIdInstalacion = instIdInstalacion;
	}

	public Date getInstFechaInstalacion() {
		return this.instFechaInstalacion;
	}

	public void setInstFechaInstalacion(Date instFechaInstalacion) {
		this.instFechaInstalacion = instFechaInstalacion;
	}

	public String getInstObservacion() {
		return this.instObservacion;
	}

	public void setInstObservacion(String instObservacion) {
		this.instObservacion = instObservacion;
	}

	public Dependencia getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instIdInstalacion == null) ? 0 : instIdInstalacion.hashCode());
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
		Instalacion other = (Instalacion) obj;
		if (instIdInstalacion == null) {
			if (other.instIdInstalacion != null)
				return false;
		} else if (!instIdInstalacion.equals(other.instIdInstalacion))
			return false;
		return true;
	}

}