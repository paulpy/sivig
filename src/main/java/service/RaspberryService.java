package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Equipo;
import model.Raspberry;


@Stateless
public class RaspberryService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<Raspberry> raspberryEventSrc;
	public void registrarRaspberry(Raspberry raspberry) throws Exception{
		logger.info("Registrado "+ raspberry.getRaspNombre());
		em.persist(raspberry);
		raspberryEventSrc.fire(raspberry);
	}
	public List<Raspberry> listRaspberry(){
		List<Raspberry> raspberrys = null;
		TypedQuery<Raspberry> query = em.createQuery("FROM Raspberry", Raspberry.class);
		raspberrys = query.getResultList();
		return raspberrys;
	}
	public Raspberry getRaspberry(Integer raspid){
		Raspberry raspberry = null;
		raspberry = em.find(Raspberry.class, raspid);
		return raspberry;
	}
	public Boolean insertConjunto(Equipo equipo, Raspberry raspberry){
		logger.info("Registrar conjunto"+raspberry.getRaspIdRaspberry()+equipo.getEquiSerieEquipo());
		try {
			em.persist(raspberry);
			equipo.setRaspberry(raspberry);
			em.persist(equipo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("entro al cath no funca lo que hice "+e);
			return false;
		}
	}
	public void actualizarRaspberry(Raspberry raspberry) throws Exception{
		logger.info("Registrado "+ raspberry.getRaspNombre());
		em.merge(raspberry);
		raspberryEventSrc.fire(raspberry);
	}
	
	public Raspberry getRaspIdentificador(String identificadorRaspberry){
		Raspberry raspberry = null;
		TypedQuery<Raspberry> query = em.createQuery("FROM Raspberry AS r WHERE r.raspNombre = :raspNombre", Raspberry.class);
		raspberry = query.setParameter("raspNombre", identificadorRaspberry).getSingleResult();
		return raspberry;
	}
	
	public Raspberry getRaspberryJson(JSONArray datosConjunto){
		Raspberry raspRecu = null;
		JSONObject elementoJsonDatos = new JSONObject();
		for (int i = 0; i < datosConjunto.length(); i++){
			elementoJsonDatos = datosConjunto.getJSONObject(i);
			raspRecu = getRaspIdentificador(elementoJsonDatos.getString("raspIdentificador"));
		}
		return raspRecu;
	}
}
