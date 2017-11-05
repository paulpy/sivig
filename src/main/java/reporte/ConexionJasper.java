package reporte;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionJasper {
	private Connection conexion = null;
	private String urlBD = "jdbc:postgresql://localhost:5432/sivig";
	private String userBD = "postgres";
	private String passBD = "destruccion";

	public Connection getConexion() throws Exception {
		Class.forName("org.postgresql.Driver");
		conexion = DriverManager.getConnection(urlBD, userBD, passBD);
		return conexion;
	}
}
