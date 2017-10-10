package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contratos database table.
 * 
 */
@Entity
@Table(name="contratos")
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cont_id_contrato")
	private Integer contIdContrato;

	@Column(name="cont_activo")
	private Boolean contActivo;

	@Temporal(TemporalType.DATE)
	@Column(name="cont_fecha_finalizacion")
	private Date contFechaFinalizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="cont_fecha_inicio")
	private Date contFechaInicio;

	@Column(name="cont_identificador_contrato")
	private String contIdentificadorContrato;

	@Column(name="cont_observacion")
	private String contObservacion;

	//bi-directional many-to-one association to Entidade
	@ManyToOne
	@JoinColumn(name="cont_id_entidad")
	private Entidad entidad;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cont_id_funcionario")
	private Funcionario funcionario;

	//bi-directional many-to-one association to Mantenimiento
	@OneToMany(mappedBy="contrato")
	private List<Mantenimiento> mantenimientos;

	public Contrato() {
	}

	public Integer getContIdContrato() {
		return this.contIdContrato;
	}

	public void setContIdContrato(Integer contIdContrato) {
		this.contIdContrato = contIdContrato;
	}

	public Boolean getContActivo() {
		return this.contActivo;
	}

	public void setContActivo(Boolean contActivo) {
		this.contActivo = contActivo;
	}

	public Date getContFechaFinalizacion() {
		return this.contFechaFinalizacion;
	}

	public void setContFechaFinalizacion(Date contFechaFinalizacion) {
		this.contFechaFinalizacion = contFechaFinalizacion;
	}

	public Date getContFechaInicio() {
		return this.contFechaInicio;
	}

	public void setContFechaInicio(Date contFechaInicio) {
		this.contFechaInicio = contFechaInicio;
	}

	public String getContIdentificadorContrato() {
		return this.contIdentificadorContrato;
	}

	public void setContIdentificadorContrato(String contIdentificadorContrato) {
		this.contIdentificadorContrato = contIdentificadorContrato;
	}

	public String getContObservacion() {
		return contObservacion;
	}

	public void setContObservacion(String contObservacion) {
		this.contObservacion = contObservacion;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Mantenimiento> getMantenimientos() {
		return this.mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public Mantenimiento addMantenimiento(Mantenimiento mantenimiento) {
		getMantenimientos().add(mantenimiento);
		mantenimiento.setContrato(this);

		return mantenimiento;
	}

	public Mantenimiento removeMantenimiento(Mantenimiento mantenimiento) {
		getMantenimientos().remove(mantenimiento);
		mantenimiento.setContrato(null);

		return mantenimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contIdContrato == null) ? 0 : contIdContrato.hashCode());
		result = prime * result + ((contIdentificadorContrato == null) ? 0 : contIdentificadorContrato.hashCode());
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
		Contrato other = (Contrato) obj;
		if (contIdContrato == null) {
			if (other.contIdContrato != null)
				return false;
		} else if (!contIdContrato.equals(other.contIdContrato))
			return false;
		if (contIdentificadorContrato == null) {
			if (other.contIdentificadorContrato != null)
				return false;
		} else if (!contIdentificadorContrato.equals(other.contIdentificadorContrato))
			return false;
		return true;
	}

}