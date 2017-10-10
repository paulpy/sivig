package sivig.sivig.rest;

import javax.enterprise.context.RequestScoped;
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

import service.HistoricoEquipoEstadoService;

@Path("/historicoequipo")
@RequestScoped
public class HistoricosEquiposRESTService  {
	@Inject
	private HistoricoEquipoEstadoService historicoEquipoEstadoService;
	
	@GET
	@Path("/ultimosincronizado/{serieEquipo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer ultimoIdSincronizado(@PathParam("serieEquipo") String serieEquipo){
		return historicoEquipoEstadoService.getIDendSincro(serieEquipo);
	}
	
	@POST
	@Path("/sincronizarHistoricoEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response sincronizarHistoricoEstado(JSONArray voHistoricoEstadosEquipo){
		 Response.ResponseBuilder builder = null;
		 try {
			 historicoEquipoEstadoService.insertarlog(voHistoricoEstadosEquipo);
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
