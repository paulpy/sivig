package converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import model.Funcionario;
import service.FuncionarioService;

@Named("funcionarioConverter")
@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter{
	@Inject
	Logger log;
	@Inject
	FuncionarioService funcionarioService;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		if(value == null || "".equals(value.trim())){
			return null;
		}
		Integer id = Integer.parseInt(value);
		try {
			Funcionario funcionario = funcionarioService.getFuncionario(id);
			if(funcionario == null){
				throw new ConverterException("Funcionario not found key: " + id);
			}
			return funcionario;
		} catch (Exception e) {
			// TODO: handle exception
			log.warning("Error: " + e.getMessage() + "; Cause: " + e.getCause());
			throw new ConverterException("Funcionario not found for key: " + id, e);
		}
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if(value instanceof Funcionario){
			Funcionario funcionario = (Funcionario) value;
			return funcionario.getFuncIdFuncionario()+"";
		} else {
			return "";
		}
	}
}
