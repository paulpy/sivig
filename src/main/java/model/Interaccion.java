package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the interacciones database table.
 * 
 */
@Entity
@Table(name="interacciones")
@NamedQuery(name="Interaccion.findAll", query="SELECT i FROM Interaccion i")
public class Interaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inte_id_interaccion")
	private Integer inteIdInteraccion;

	@Column(name="inte_activo")
	private Boolean inteActivo;
	
	@Column(name="inte_comando")
	private String inteComando;
	
	@Column(name="inte_codigo")
	private Integer inteCodigo;

	@Column(name="inte_fecha_hora_realizado")
	private Timestamp inteFechaHoraRealizado;

	@Column(name="inte_fecha_hora_solicitud")
	private Timestamp inteFechaHoraSolicitud;
	
	@Column(name="inte_resultado")
	private String inteResultado;

	//bi-directional many-to-one association to Accion
	@ManyToOne
	@JoinColumn(name="inte_cod_accion")
	private Accion accion;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="inte_id_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to Raspberry
	@ManyToOne
	@JoinColumn(name="inte_id_raspberry")
	private Raspberry raspberry; 

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="inte_id_usuario")
	private Usuario usuario;

	public Interaccion() {
	}

	public Integer getInteIdInteraccion() {
		return this.inteIdInteraccion;
	}

	public void setInteIdInteraccion(Integer inteIdInteraccion) {
		this.inteIdInteraccion = inteIdInteraccion;
	}

	public Boolean getInteActivo() {
		return this.inteActivo;
	}

	public void setInteActivo(Boolean inteActivo) {
		this.inteActivo = inteActivo;
	}

	public Timestamp getInteFechaHoraRealizado() {
		return this.inteFechaHoraRealizado;
	}

	public void setInteFechaHoraRealizado(Timestamp inteFechaHoraRealizado) {
		this.inteFechaHoraRealizado = inteFechaHoraRealizado;
	}

	public Timestamp getInteFechaHoraSolicitud() {
		return this.inteFechaHoraSolicitud;
	}

	public void setInteFechaHoraSolicitud(Timestamp inteFechaHoraSolicitud) {
		this.inteFechaHoraSolicitud = inteFechaHoraSolicitud;
	}

	public Accion getAccion() {
		return this.accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Raspberry getRaspberry() {
		return this.raspberry;
	}

	public void setRaspberry(Raspberry raspberry) {
		this.raspberry = raspberry;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getInteComando() {
		return inteComando;
	}

	public void setInteComando(String inteComando) {
		this.inteComando = inteComando;
	}

	public Integer getInteCodigo() {
		return inteCodigo;
	}

	public void setInteCodigo(Integer inteCodigo) {
		this.inteCodigo = inteCodigo;
	}

	public String getInteResultado() {
		return inteResultado;
	}

	public void setInteResultado(String inteResultado) {
		this.inteResultado = inteResultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inteIdInteraccion == null) ? 0 : inteIdInteraccion.hashCode());
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
		Interaccion other = (Interaccion) obj;
		if (inteIdInteraccion == null) {
			if (other.inteIdInteraccion != null)
				return false;
		} else if (!inteIdInteraccion.equals(other.inteIdInteraccion))
			return false;
		return true;
	}
	
}