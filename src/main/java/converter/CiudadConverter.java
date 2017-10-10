package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Ciudad;
import service.CiudadService;

@Named("ciudadConverter")
@FacesConverter(forClass = Ciudad.class)
public class CiudadConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	CiudadService ciudadService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Ciudad ciudad = ciudadService.getCiudad(id);
			if(ciudad == null){
				throw new ConverterException("Ciudad not found for key: " + id);
			}
			return ciudad;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Ciudad not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Ciudad){
			Ciudad ciudad = (Ciudad) value;
			return ciudad.getCiudIdCiudad()+"";
		} else {
			return "";
		}
	}
}
