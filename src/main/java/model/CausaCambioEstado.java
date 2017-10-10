package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the causa_cambios_estados database table.
 * 
 */
@Entity
@Table(name="causa_cambios_estados")
@NamedQuery(name="CausaCambioEstado.findAll", query="SELECT c FROM CausaCambioEstado c")
public class CausaCambioEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cces_id_causa_cambio")
	private Integer ccesIdCausaCambio;

	@Column(name="cces_causa_estado")
	private String ccesCausaEstado;

	//bi-directional many-to-one association to HistoricoEquiposEstado
	@OneToMany(mappedBy="causaCambioEstado")
	private List<HistoricoEquipoEstado> historicoEquipoEstados;

	//bi-directional many-to-one association to HistoricoRaspberrysEstado
	@OneToMany(mappedBy="causaCambioEstado")
	private List<HistoricoRaspberryEstado> historicoRaspberryEstados;

	public CausaCambioEstado() {
	}

	public Integer getCcesIdCausaCambio() {
		return this.ccesIdCausaCambio;
	}

	public void setCcesIdCausaCambio(Integer ccesIdCausaCambio) {
		this.ccesIdCausaCambio = ccesIdCausaCambio;
	}

	public String getCcesCausaEstado() {
		return this.ccesCausaEstado;
	}

	public void setCcesCausaEstado(String ccesCausaEstado) {
		this.ccesCausaEstado = ccesCausaEstado;
	}

	public List<HistoricoEquipoEstado> getHistoricoEquiposEstados() {
		return this.historicoEquipoEstados;
	}

	public void setHistoricoEquiposEstados(List<HistoricoEquipoEstado> historicoEquipoEstados) {
		this.historicoEquipoEstados = historicoEquipoEstados;
	}

	public HistoricoEquipoEstado addHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().add(historicoEquipoEstado);
		historicoEquipoEstado.setCausaCambiosEstado(this);

		return historicoEquipoEstado;
	}

	public HistoricoEquipoEstado removeHistoricoEquiposEstado(HistoricoEquipoEstado historicoEquipoEstado) {
		getHistoricoEquiposEstados().remove(historicoEquipoEstado);
		historicoEquipoEstado.setCausaCambiosEstado(null);

		return historicoEquipoEstado;
	}

	public List<HistoricoRaspberryEstado> getHistoricoRaspberrysEstados() {
		return this.historicoRaspberryEstados;
	}

	public void setHistoricoRaspberrysEstados(List<HistoricoRaspberryEstado> historicoRaspberryEstados) {
		this.historicoRaspberryEstados = historicoRaspberryEstados;
	}

	public HistoricoRaspberryEstado addHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().add(historicoRaspberryEstado);
		historicoRaspberryEstado.setCausaCambiosEstado(this);

		return historicoRaspberryEstado;
	}

	public HistoricoRaspberryEstado removeHistoricoRaspberrysEstado(HistoricoRaspberryEstado historicoRaspberryEstado) {
		getHistoricoRaspberrysEstados().remove(historicoRaspberryEstado);
		historicoRaspberryEstado.setCausaCambiosEstado(null);

		return historicoRaspberryEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ccesIdCausaCambio == null) ? 0 : ccesIdCausaCambio.hashCode());
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
		CausaCambioEstado other = (CausaCambioEstado) obj;
		if (ccesIdCausaCambio == null) {
			if (other.ccesIdCausaCambio != null)
				return false;
		} else if (!ccesIdCausaCambio.equals(other.ccesIdCausaCambio))
			return false;
		return true;
	}

}