package sivig.sivig.rest;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import service.HistoricoRaspberryEstadoService;

@Path("/historicoraspberry")
@RequestScoped
public class HistoricoRaspberrysRESTService {
	@Inject
	private HistoricoRaspberryEstadoService historicoRaspberryEstadoService;
	
	@GET
	@Path("/ultimosincronizado/{serieRaspberry}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer ultimoIdSincronizado(@PathParam("serieRaspberry") String serieRaspberry){
		return historicoRaspberryEstadoService.getIDendSincro(serieRaspberry);
	}
	
	@POST
	@Path("/sincronizarHistoricoEstado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sincronizarHistoricoEstado(JSONArray historicoEstadoRaspberry){
		Response.ResponseBuilder builder = null;
		try {
			historicoRaspberryEstadoService.insertarlog(historicoEstadoRaspberry);
			builder = Response.ok();
		} catch (Exception e) {
			// TODO: handle exception
			String mensajeerror = e.toString();
			System.out.println(mensajeerror);
			builder = Response.serverError();
		}
		return builder.build();
	}
}
