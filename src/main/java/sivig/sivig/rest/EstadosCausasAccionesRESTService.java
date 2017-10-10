package sivig.sivig.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.AccionService;
import service.CambioEstadoService;
import service.EstadoEquipoService;

@Path("/accioncausaestado")
@RequestScoped
public class EstadosCausasAccionesRESTService {
	@Inject
	private EstadoEquipoService estadoEquipoService;
	
	@Inject
	private CambioEstadoService cambioEstadoService;
	
	@Inject
	private AccionService accionService;
	
	@GET
	@Path("/estados")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listEstadoEquipo(){
		return estadoEquipoService.listEstadoEquiposNom();
	}
	@GET
	@Path("/causas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listCausasCambio(){
		return cambioEstadoService.listCambioEstadoNom();
	}
	
	@GET
	@Path("/acciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listAcciones(){
		return accionService.listAccionNom();
	}
}
