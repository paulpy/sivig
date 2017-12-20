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

import model.Equipo;
import model.HistoricoEquipoEstado;

@Stateless
public class HistoricoEquipoEstadoService {
	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;
	@Inject
	private Event<HistoricoEquipoEstado> historicoEquipoEstadoEventSrc;
	@Inject
	private CambioEstadoService cambioEstadoService;
	@Inject
	private EstadoEquipoService equipoService;
	@Inject
	private EquipoService equipoServices;
	public void registrarHistoricoEquipoEstado(HistoricoEquipoEstado historicoEquipoEstado) throws Exception{
		logger.info("Registrado "+historicoEquipoEstado.getEstadosEquipo().getEseqEstadoEquipo());
		em.persist(historicoEquipoEstado);
		historicoEquipoEstadoEventSrc.fire(historicoEquipoEstado);
	}
	
	public Equipo getIdEquipo(String serieEquipo){
		Equipo equipoRecu = null;
		TypedQuery<Equipo> query = em.createQuery("FROM Equipo AS e WHERE e.equiIdentificador = :equiIdentificador", Equipo.class);
		try {
			equipoRecu = query.setParameter("equiIdentificador", serieEquipo).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("no se encontro ningun equipo con este identificador");
			equipoRecu = null;
		}
		return equipoRecu;
	}
	
	public Integer getIDendSincro(String serieEquipo){
		List<HistoricoEquipoEstado> listIdEquipo = null;
		Integer idHistoricoEquipoEstado = null;
		Equipo idequipo = null;
		TypedQuery<Equipo> query1 = em.createQuery("FROM Equipo AS e WHERE e.equiIdentificador = :equiIdentificador", Equipo.class);
		TypedQuery<HistoricoEquipoEstado> query = em.createQuery("FROM HistoricoEquipoEstado AS hee WHERE hee.equipo.equiIdEquipo = :equipo ORDER BY hee.eqesIdExterno ASC", HistoricoEquipoEstado.class);
		try {
			idequipo = query1.setParameter("equiIdentificador", serieEquipo).getSingleResult();
			if (!idequipo.getEquiIdEquipo().equals(null)){
				idHistoricoEquipoEstado=0;
			}
			listIdEquipo =  query.setParameter("equipo", idequipo.getEquiIdEquipo()).getResultList();
			for(HistoricoEquipoEstado historicoID : listIdEquipo){
				idHistoricoEquipoEstado = historicoID.getEqesIdExterno();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("no se encontro ningun equipo con este identificador");
			idHistoricoEquipoEstado = -1;
		}
		return idHistoricoEquipoEstado;
	}
	
	public void insertarlog(JSONArray logEstado){
		JSONObject elementoJsonLog = new JSONObject();
		for (int i = 0; i < logEstado.length(); i++) {
			HistoricoEquipoEstado historicoEquipoEstado = new HistoricoEquipoEstado();
			elementoJsonLog = logEstado.getJSONObject(i);
			historicoEquipoEstado.setEqesIdExterno(elementoJsonLog.getInt("id"));
			historicoEquipoEstado.setEqesMomentoEstado(Timestamp.valueOf(elementoJsonLog.getString("momento")));
			historicoEquipoEstado.setCausaCambiosEstado(cambioEstadoService.getCausaCambioEstado(elementoJsonLog.getString("causa")));
			historicoEquipoEstado.setEstadosEquipo(equipoService.getEstadoEquipo(elementoJsonLog.getString("estado")));
			historicoEquipoEstado.setEquipo(equipoServices.getEquipoIdentidicador(elementoJsonLog.getString("equipo")));
			try {
				registrarHistoricoEquipoEstado(historicoEquipoEstado);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
	}
	public List<HistoricoEquipoEstado> listHistoricoE(Integer idequipo){
		List<HistoricoEquipoEstado> listarHistEquipo = null;
		TypedQuery<HistoricoEquipoEstado> query = em.createQuery("FROM HistoricoEquipoEstado AS hee WHERE hee.equipo.equiIdEquipo = :equipo ORDER BY hee.eqesMomentoEstado DESC", HistoricoEquipoEstado.class);
		listarHistEquipo = query.setParameter("equipo", idequipo).getResultList();
		return listarHistEquipo;
	}
	
	public List<HistoricoEquipoEstado> listHistoricoEAll(){
		List<HistoricoEquipoEstado> listarHistEquipo = null;
		TypedQuery<HistoricoEquipoEstado> query = em.createQuery("FROM HistoricoEquipoEstado AS hee ORDER BY hee.eqesMomentoEstado DESC", HistoricoEquipoEstado.class);
		listarHistEquipo = query.getResultList();
		return listarHistEquipo;
	}
	
	public List<String> listHistoricoEquipoCambioEstado(Integer idequipo){
		List<String> listarHistEquipo = null;
		TypedQuery<String> query = em.createQuery("SELECT ee.eseqEstadoEquipo FROM HistoricoEquipoEstado AS hee, EstadoEquipo AS ee WHERE hee.equipo.equiIdEquipo = :equipo AND hee.estadoEquipo.eseqIdEstadoEquipo = ee.eseqIdEstadoEquipo ORDER BY hee.eqesMomentoEstado DESC", String.class);
		listarHistEquipo = query.setParameter("equipo", idequipo).getResultList();
		return listarHistEquipo;
	}
	
	public HistoricoEquipoEstado ultimoEstadoEquipo(Integer idequipo){
		HistoricoEquipoEstado ultimoEstado = null;
		TypedQuery<HistoricoEquipoEstado> query = em.createQuery("FROM HistoricoEquipoEstado AS hee WHERE hee.equipo.equiIdEquipo = :equipo ORDER BY hee.eqesMomentoEstado ASC", HistoricoEquipoEstado.class);
		List<HistoricoEquipoEstado> listEquiEstado = query.setParameter("equipo", idequipo).getResultList();
		try {
			for(HistoricoEquipoEstado historicoEE : listEquiEstado){
				ultimoEstado = historicoEE;
			}
			System.out.println(ultimoEstado.getEstadosEquipo().getEseqEstadoEquipo());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return ultimoEstado;
	}
}
