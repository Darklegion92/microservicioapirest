
package modelos.conexion;

import java.sql.*;

public class Conexion {

	private String nombreBd = "apirest";
	/*private String usuario = "domicilios";
	private String password = "Domicilios83731";*/
	private String usuario = "root";
	private String password = "1234";
	private String url = "jdbc:mysql://localhost:3306/" + nombreBd;

	Connection conn = null; // creamos un objeto de tipo conexion

	public Conexion() { // generamos el constructor de la clase
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, usuario, password);
				if (conn != null) {
					System.out.println("Conexion Exitosa a la BD: " + nombreBd);
				}// creamos esta conecion con los datos
			} catch (ClassNotFoundException | SQLException e) {
			
				e.printStackTrace();
			} // java busca este conector
	}

	public Connection getConnection() {
		return conn;

	}

	public void desconectar() {
		conn = null;
	}
}