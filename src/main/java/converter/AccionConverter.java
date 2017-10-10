package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Accion;
import service.AccionService;

@Named("accionConverter")
@FacesConverter(forClass = Accion.class)
public class AccionConverter implements Converter{
	@Inject
	Logger log;
	@Inject 
	AccionService accionService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Accion accion = accionService.getAccion(id);
			if(accion == null){
				throw new ConverterException("Accion not found for key: " + id);
			}
			return accion;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Accion not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Accion){
			Accion accion = (Accion) value;
			return accion.getAcciIdAccion()+"";
		} else {
			return "";
		}
	}
}
