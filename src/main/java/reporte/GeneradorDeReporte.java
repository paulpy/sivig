package reporte;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class GeneradorDeReporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	Logger log;

	@ManagedProperty(value = "#{conexionConsulta}")
	private ConexionConsulta conexionConsulta;
	
	ConexionJasper cjr = new ConexionJasper();

	public StreamedContent generarReporte(String rutareporte, Map<String, Object> parametros) {
		try {
			ConexionConsulta conn = new ConexionConsulta();

			java.io.InputStream reportTemplate = this.getClass().getResourceAsStream(rutareporte);
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			JasperDesign jasperDesign;

			jasperDesign = JRXmlLoader.load(reportTemplate);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn.conectionConsultaReporte());
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			// ver Primefaces User Guide, sección 3.45 -> FileDownload
			return new DefaultStreamedContent(new ByteArrayInputStream(pdf), "application/pdf", "reporte.pdf");
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocurrió un error al generar el reporte. \n" + e.getMessage(), ""));
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex.toString().startsWith("java.io.FileNotFoundException")) {
				ex.toString();
			}
		}
		return null;
	}

	public ConexionConsulta getConexionConsulta() {
		return conexionConsulta;
	}

	public void setConexionConsulta(ConexionConsulta conexionConsulta) {
		this.conexionConsulta = conexionConsulta;
	}

}
