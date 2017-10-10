package service;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import model.HistoricoRaspberryEstado;
import model.Raspberry;

@Stateless
public class HistoricoRaspberryEstadoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<HistoricoRaspberryEstado> historicoRaspberrryEstadoEventSrc;
	@Inject
	private CambioEstadoService cambioEstadoService;
	@Inject
	private EstadoEquipoService equipoService;
	@Inject
	private RaspberryService raspberryService;
	public void registrarHistoricoRaspberryEstadoService(HistoricoRaspberryEstado historicoRaspberryEstado) throws Exception{
		logger.info("Registrado "+historicoRaspberryEstado.getEstadosEquipo().getEseqEstadoEquipo());
		em.persist(historicoRaspberryEstado);
		historicoRaspberrryEstadoEventSrc.fire(historicoRaspberryEstado);
	}
	
	public Integer getIDendSincro(String serieRaspberry){
		List<HistoricoRaspberryEstado> listIdRasp = null;
		Integer idHistoricoRaspberryEstado = null;
		Raspberry idRaspberry = null;
		TypedQuery<Raspberry> queryRasp = em.createQuery("FROM Raspberry AS r WHERE r.raspNombre = :raspNombre", Raspberry.class);
		TypedQuery<HistoricoRaspberryEstado> queryEstRasp = em.createQuery("FROM HistoricoRaspberryEstado AS hre WHERE hre.raspberry.raspIdRaspberry = :raspberry ORDER BY hre.raesIdExterno ASC", HistoricoRaspberryEstado.class);
		try {
			idRaspberry = queryRasp.setParameter("raspNombre", serieRaspberry).getSingleResult();
			if(!idRaspberry.getRaspIdRaspberry().equals(null)){
				idHistoricoRaspberryEstado = 0;
			}
			listIdRasp = queryEstRasp.setParameter("raspberry", idRaspberry.getRaspIdRaspberry()).getResultList();
			for(HistoricoRaspberryEstado historicoID : listIdRasp){
				idHistoricoRaspberryEstado = historicoID.getRaesIdExterno();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("no se encontro ningun raspberry con este identificador"+serieRaspberry);
			idHistoricoRaspberryEstado = -1;
		}
		return idHistoricoRaspberryEstado;
	}
	
	public void insertarlog(JSONArray logestado){
		JSONObject elementoJsonLog = new JSONObject();
		for (int i = 0; i < logestado.length(); i++){
			HistoricoRaspberryEstado historicoRaspberryEstado = new HistoricoRaspberryEstado();
			elementoJsonLog = logestado.getJSONObject(i);
			historicoRaspberryEstado.setRaesIdExterno(elementoJsonLog.getInt("id"));
			historicoRaspberryEstado.setRaesMomentoCambio(Timestamp.valueOf(elementoJsonLog.getString("momento")));
			historicoRaspberryEstado.setCausaCambiosEstado(cambioEstadoService.getCausaCambioEstado(elementoJsonLog.getString("causa")));
			historicoRaspberryEstado.setEstadosEquipo(equipoService.getEstadoEquipo(elementoJsonLog.getString("estado")));
			historicoRaspberryEstado.setRaspberry(raspberryService.getRaspIdentificador(elementoJsonLog.getString("equipo")));
			try {
				registrarHistoricoRaspberryEstadoService(historicoRaspberryEstado);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
	}
	
	public List<HistoricoRaspberryEstado> listHistoricoR(Integer idrasp){
		// TODO Auto-generated method stub
		List<HistoricoRaspberryEstado> listarHistoricoR = null;
		TypedQuery<HistoricoRaspberryEstado> query = em.createQuery("FROM HistoricoRaspberryEstado AS hre WHERE hre.raspberry.raspIdRaspberry = :raspberry ORDER BY hre.raesMomentoCambio DESC", HistoricoRaspberryEstado.class);
		listarHistoricoR = query.setParameter("raspberry", idrasp).getResultList();
		return listarHistoricoR;
	}
	
	public List<HistoricoRaspberryEstado> listHistoricoRAll(){
		// TODO Auto-generated method stub
		List<HistoricoRaspberryEstado> listarHistoricoR = null;
		TypedQuery<HistoricoRaspberryEstado> query = em.createQuery("FROM HistoricoRaspberryEstado AS hre ORDER BY hre.raesMomentoCambio DESC", HistoricoRaspberryEstado.class);
		listarHistoricoR = query.getResultList();
		return listarHistoricoR;
	}
}
