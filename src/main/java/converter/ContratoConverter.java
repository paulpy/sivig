package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Contrato;
import service.ContratoService;

@Named("contratoConverter")
@FacesConverter(forClass = Contrato.class)
public class ContratoConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	ContratoService contratoService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try{
			Contrato contrato = contratoService.getContrato(id);
			if(contrato == null){
				throw new ConverterException("Contrato not found for key: "+id);
			}
			return contrato;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Contrato not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if(value instanceof Contrato){
			Contrato contrato = (Contrato) value;
			return contrato.getContIdContrato()+"";
		} else {
			return "";
		}
	}
}
