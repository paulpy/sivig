package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Calle;
import service.CalleService; 

@Named("calleConverter")
@FacesConverter(forClass = Calle.class)
public class CalleConverter implements Converter{
	@Inject
	Logger log;
	@Inject 
	CalleService calleService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Calle calle = calleService.getCalle(id);
			if(calle == null){
				throw new ConverterException("Calle not found for key: " + id);
			}
			return calle;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Calle not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Calle){
			Calle calle = (Calle) value;
			return calle.getCallIdCalle()+"";
		} else {
			return "";
		}
	}
}
