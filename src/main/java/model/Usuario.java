package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usua_id_usuario")
	private Integer usuaIdUsuario;

	@Column(name="usua_activo")
	private Boolean usuaActivo;

	@Column(name="usua_pass")
	private String usuaPass;

	@Column(name="usua_tipo")
	private String usuaTipo;

	@Column(name="usua_usuario")
	private String usuaUsuario;

	//bi-directional many-to-one association to Auditoria
	@OneToMany(mappedBy="usuario")
	private List<Auditoria> auditorias;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="usua_id_funcionario")
	private Funcionario funcionario;
	
	//bi-directional many-to-one association to Interaccion
	@OneToMany(mappedBy="usuario")
	private List<Interaccion> interacciones;

	public Usuario() {
	}

	public Integer getUsuaIdUsuario() {
		return this.usuaIdUsuario;
	}

	public void setUsuaIdUsuario(Integer usuaIdUsuario) {
		this.usuaIdUsuario = usuaIdUsuario;
	}

	public Boolean getUsuaActivo() {
		return this.usuaActivo;
	}

	public void setUsuaActivo(Boolean usuaActivo) {
		this.usuaActivo = usuaActivo;
	}

	public String getUsuaPass() {
		return this.usuaPass;
	}

	public void setUsuaPass(String usuaPass) {
		this.usuaPass = usuaPass;
	}

	public String getUsuaTipo() {
		return this.usuaTipo;
	}

	public void setUsuaTipo(String usuaTipo) {
		this.usuaTipo = usuaTipo;
	}

	public String getUsuaUsuario() {
		return this.usuaUsuario;
	}

	public void setUsuaUsuario(String usuaUsuario) {
		this.usuaUsuario = usuaUsuario;
	}

	public List<Auditoria> getAuditorias() {
		return this.auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public Auditoria addAuditoria(Auditoria auditoria) {
		getAuditorias().add(auditoria);
		auditoria.setUsuario(this);

		return auditoria;
	}

	public Auditoria removeAuditoria(Auditoria auditoria) {
		getAuditorias().remove(auditoria);
		auditoria.setUsuario(null);

		return auditoria;
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
		result = prime * result + ((usuaIdUsuario == null) ? 0 : usuaIdUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (usuaIdUsuario == null) {
			if (other.usuaIdUsuario != null)
				return false;
		} else if (!usuaIdUsuario.equals(other.usuaIdUsuario))
			return false;
		return true;
	}

}