package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Entidad;
import service.EntidadService;

@Named("entidadConverter")
@FacesConverter(forClass = Entidad.class)
public class EntidadConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	EntidadService entidadService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Entidad entidad = entidadService.getEntidad(id);
			if(entidad == null){
				throw new ConverterException("Entidad not found for key: "+ id);
			}
			return entidad;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: "+e.getMessage() + "; Cause: "+ e.getCause());
			throw new ConverterException("Entidad not found for key: "+ id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if(value instanceof Entidad){
			Entidad entidad = (Entidad) value;
			return entidad.getEntiIdEntidad()+"";
		} else {
			return "";
		}
	}
}
