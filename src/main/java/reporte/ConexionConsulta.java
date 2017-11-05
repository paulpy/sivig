package reporte;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionConsulta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConexionConsulta(){
		
	}
	
	public Connection conectionConsultaReporte(){
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:jboss/datasources/sivigDS");
			if(ds.getConnection() != null){
				System.out.println("entra a la bd");
			}
			return (ds.getConnection());
		} catch (NamingException e) {
			// TODO: handle exception
			System.out.println("Nombre JNI de conexión incorrecta");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Nombre JNI de conexión incorrecta");
			e.printStackTrace();
			return null;
		}
	}

}
