package sivig.sivig.model;

public class EnvioInteraccion {
	private Integer idInteraccion;
	private Integer codInteraccion;
	private String comando;
	private Integer idAccion;
	
	public EnvioInteraccion(){
		
	}
	public Integer getIdInteraccion() {
		return idInteraccion;
	}
	public void setIdInteraccion(Integer idInteraccion) {
		this.idInteraccion = idInteraccion;
	}
	public Integer getCodInteraccion() {
		return codInteraccion;
	}
	public void setCodInteraccion(Integer codInteraccion) {
		this.codInteraccion = codInteraccion;
	}
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}
	public Integer getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}
}
