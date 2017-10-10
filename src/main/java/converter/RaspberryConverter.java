package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Raspberry;
import service.RaspberryService;

@Named("raspberryConverter")
@FacesConverter(forClass = Raspberry.class)
public class RaspberryConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	RaspberryService raspberryService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || "".equals(value.trim())) {
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Raspberry raspberry= raspberryService.getRaspberry(id);
			if (raspberry == null) {
				throw new ConverterException("Raspberry not found for key: " + id);
			}
			return raspberry;
		} catch (Exception e) {
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Raspberry not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Raspberry ) {
			Raspberry  raspberry = (Raspberry) value;
			return raspberry.getRaspIdRaspberry() + "";
		} else {
			return "";
		}
	}
}
