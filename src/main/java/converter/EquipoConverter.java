package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Equipo;
import service.EquipoService;

@Named("equipoConverter")
@FacesConverter(forClass = Equipo.class)

public class EquipoConverter implements Converter {
	@Inject
	Logger log;
	@Inject
	EquipoService equipoService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Equipo equipo = equipoService.getEquipo(id);
			if(equipo == null){
				throw new ConverterException("Equipo not found for key: " + id);
			}
			return equipo;
		}catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Equipo not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value instanceof Equipo){
			Equipo equipo = (Equipo) value;
			return equipo.getEquiIdEquipo()+"";
		} else {
			return "";
		}
	}
}
