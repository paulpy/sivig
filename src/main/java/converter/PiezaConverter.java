package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Pieza;
import service.PiezaService;

@Named("piezaConverter")
@FacesConverter(forClass = Pieza.class)
public class PiezaConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	PiezaService piezaService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Pieza pieza = piezaService.getPieza(id);
			if(pieza == null){
				throw new ConverterException("Pieza not found for key: " + id);
			}
			return pieza;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Calle not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Pieza){
			Pieza pieza = (Pieza) value;
			return pieza.getPiezIdPieza()+"";
		} else {
			return "";
		}
	}
}
