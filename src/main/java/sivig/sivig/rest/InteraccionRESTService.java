package sivig.sivig.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import service.InteraccionService;
import service.RaspberryService;
import sivig.sivig.model.EnvioInteraccion;

@Path("/interaccion")
@RequestScoped
public class InteraccionRESTService {
	@Inject
	private InteraccionService interaccionService;
	@Inject
	private RaspberryService raspberryService;
	
	@POST
	@Path("/interaccionpendiente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<EnvioInteraccion> interaccionPendienteConj(JSONArray datosConjunto){
		return interaccionService.listInteracciones(raspberryService.getRaspberryJson(datosConjunto));
	}
	
	@POST
	@Path("/interaccionactualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarInteraccion(JSONArray datosAActualizar){
		Response.ResponseBuilder builder = null;
		try {
			interaccionService.actualizarInteracciones(datosAActualizar);
			builder = Response.ok();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return builder.build();
	}
}
