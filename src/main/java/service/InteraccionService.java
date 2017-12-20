package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Interaccion;
import model.Raspberry;
import sivig.sivig.model.EnvioInteraccion;

@Stateless
public class InteraccionService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Interaccion> interaccionEventSrc;
	@Inject
	private RaspberryService raspberryService;
	public void registrarInteraccion(Interaccion interaccion) throws Exception{
		logger.info("Registrado "+interaccion.getAccion().getAcciAccion());
		em.persist(interaccion);
		interaccionEventSrc.fire(interaccion);
	}
	
	public void actualizarInteraccion(Interaccion interaccion) throws Exception{
		logger.info("Actualizado "+interaccion.getAccion().getAcciAccion());
		em.merge(interaccion);
		interaccionEventSrc.fire(interaccion);
	}
	
	public Interaccion getInteraccion(Integer id){
		Interaccion interaccion = null;
		interaccion = em.find(Interaccion.class, id);
		return interaccion;
	}
	
	public List<Interaccion> listInteraccion(){
		List<Interaccion> listInteraccion = null;
		TypedQuery<Interaccion> query = em.createQuery("FROM Interaccion", Interaccion.class);
		listInteraccion = query.getResultList();
		return listInteraccion;
	}
	
	public List<Interaccion> listInteraccionesLog(Raspberry raspberry){
		List<Interaccion> listainteraccion = null;
		TypedQuery<Interaccion> query = em.createQuery("FROM Interaccion AS inte WHERE inte.raspberry.raspIdRaspberry = :raspberry ORDER BY inte.inteIdInteraccion DESC", Interaccion.class);
		try {
			listainteraccion = query.setParameter("raspberry", raspberry.getRaspIdRaspberry()).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("error ");
		}
		return listainteraccion;
	}
	
	public List<EnvioInteraccion> listInteracciones(Raspberry rasp){
		List<Interaccion> interaccionesConjunto = null;
		EnvioInteraccion envioparalist = new EnvioInteraccion();
		List<EnvioInteraccion> listenvio = new ArrayList<>();
		TypedQuery<Interaccion> query = em.createQuery("FROM Interaccion AS inte WHERE inte.raspberry.raspIdRaspberry = :raspberry AND inte.inteActivo = true", Interaccion.class);
		try {
			interaccionesConjunto = query.setParameter("raspberry", rasp.getRaspIdRaspberry()).getResultList();
			for(Interaccion interaccion : interaccionesConjunto){
				envioparalist.setCodInteraccion(interaccion.getInteCodigo());
				envioparalist.setComando(interaccion.getInteComando());
				envioparalist.setIdAccion(interaccion.getAccion().getAcciIdAccion());
				envioparalist.setIdInteraccion(interaccion.getInteIdInteraccion());
				listenvio.add(envioparalist);
				interaccion.setInteActivo(false);
				actualizarInteraccion(interaccion);
			}
			Timestamp tiempoactual = new Timestamp(System.currentTimeMillis());
			rasp.setRaspUltimaComunicacion(tiempoactual);
			raspberryService.actualizarRaspberry(rasp);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("error ");
		}
		return listenvio;
	}
	
	public Boolean actualizarInteracciones(JSONArray datosAActualizar){
		JSONObject elementoJsonInter = new JSONObject();
		for(int i = 0; i < datosAActualizar.length(); i++){
			Interaccion interaccion = null;
			elementoJsonInter =datosAActualizar.getJSONObject(i);
			interaccion = getInteraccion(elementoJsonInter.getInt("id"));
			interaccion.setInteFechaHoraRealizado(Timestamp.valueOf(elementoJsonInter.getString("realizado")));
			try {
				actualizarInteraccion(interaccion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
