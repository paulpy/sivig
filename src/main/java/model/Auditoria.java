package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="audi_id_auditoria")
	private Integer audiIdAuditoria;

	@Column(name="audi_detalle_accion")
	private String audiDetalleAccion;

	@Column(name="audi_momento_accion")
	private Timestamp audiMomentoAccion;

	@Column(name="audi_tabla")
	private String audiTabla;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="audi_id_usuario")
	private Usuario usuario;

	public Auditoria() {
	}

	public Integer getAudiIdAuditoria() {
		return this.audiIdAuditoria;
	}

	public void setAudiIdAuditoria(Integer audiIdAuditoria) {
		this.audiIdAuditoria = audiIdAuditoria;
	}

	public String getAudiDetalleAccion() {
		return this.audiDetalleAccion;
	}

	public void setAudiDetalleAccion(String audiDetalleAccion) {
		this.audiDetalleAccion = audiDetalleAccion;
	}

	public Timestamp getAudiMomentoAccion() {
		return this.audiMomentoAccion;
	}

	public void setAudiMomentoAccion(Timestamp audiMomentoAccion) {
		this.audiMomentoAccion = audiMomentoAccion;
	}

	public String getAudiTabla() {
		return this.audiTabla;
	}

	public void setAudiTabla(String audiTabla) {
		this.audiTabla = audiTabla;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audiIdAuditoria == null) ? 0 : audiIdAuditoria.hashCode());
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
		Auditoria other = (Auditoria) obj;
		if (audiIdAuditoria == null) {
			if (other.audiIdAuditoria != null)
				return false;
		} else if (!audiIdAuditoria.equals(other.audiIdAuditoria))
			return false;
		return true;
	}

}