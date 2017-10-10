package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personas database table.
 * 
 */
@Entity
@Table(name="personas")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pers_id_persona")
	private Integer persIdPersona;

	@Column(name="pers_apellido")
	private String persApellido;

	@Column(name="pers_ci")
	private Integer persCi;

	@Temporal(TemporalType.DATE)
	@Column(name="pers_fecha_nacimiento")
	private Date persFechaNacimiento;

	@Column(name="pers_nombre")
	private String persNombre;

	@Column(name="pers_sexo")
	private String persSexo;

	//bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy="persona")
	private List<Funcionario> funcionarios;

	public Persona() {
	}

	public Integer getPersIdPersona() {
		return this.persIdPersona;
	}

	public void setPersIdPersona(Integer persIdPersona) {
		this.persIdPersona = persIdPersona;
	}

	public String getPersApellido() {
		return this.persApellido;
	}

	public void setPersApellido(String persApellido) {
		this.persApellido = persApellido;
	}

	public Integer getPersCi() {
		return this.persCi;
	}

	public void setPersCi(Integer persCi) {
		this.persCi = persCi;
	}

	public Date getPersFechaNacimiento() {
		return this.persFechaNacimiento;
	}

	public void setPersFechaNacimiento(Date persFechaNacimiento) {
		this.persFechaNacimiento = persFechaNacimiento;
	}

	public String getPersNombre() {
		return this.persNombre;
	}

	public void setPersNombre(String persNombre) {
		this.persNombre = persNombre;
	}

	public String getPersSexo() {
		return this.persSexo;
	}

	public void setPersSexo(String persSexo) {
		this.persSexo = persSexo;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setPersona(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setPersona(null);

		return funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((persIdPersona == null) ? 0 : persIdPersona.hashCode());
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
		Persona other = (Persona) obj;
		if (persIdPersona == null) {
			if (other.persIdPersona != null)
				return false;
		} else if (!persIdPersona.equals(other.persIdPersona))
			return false;
		return true;
	}

}