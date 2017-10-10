package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Dependencia;
import service.DependenciaService;

@Named("dependenciaConverter")
@FacesConverter(forClass = Dependencia.class)
public class DependenciaConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	DependenciaService dependenciaService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Dependencia dependencia = dependenciaService.getDependencia(id);
			if(dependencia == null){
				throw new ConverterException("Dependencia not found for key: "+id);
			}
			return dependencia;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Calle not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if(value instanceof Dependencia){
			Dependencia dependencia = (Dependencia) value;
			return dependencia.getDepeIdDependencia()+"";
		} else {
			return "";
		}
	}
}
