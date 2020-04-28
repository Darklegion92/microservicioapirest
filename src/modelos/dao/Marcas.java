package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Coordinador;
import modelos.vo.Marca;

public class Marcas {
	
	private Coordinador miCoordinador;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	public void consultar() throws SQLException {
		
		//consuta el articulo en la bd de sysplus
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miCoordinador.getConnectionFirebird();
		String consulta = "SELECT marc_nom, marc_cod FROM marcas";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		while (resultado.next()== true) {
			Marca miMarca = new Marca();
			miMarca.setIdMarca(resultado.getString("marc_cod"));
			miMarca.setNombreMarca(resultado.getString("marc_nom"));
			registrar(miMarca);
			actualizar(miMarca);
		}
		resultado.close();
	}
	
	private void actualizar(Marca miMarca) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="UPDATE marcas " + 
				"SET `nombreMarca` = '"+miMarca.getNombreMarca()+"' "+ 
				"WHERE `idmarca` = '"+miMarca.getIdMarca()+"';";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}

	private void registrar(Marca miMarca) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="INSERT IGNORE INTO marcas(idMarca,nombreMarca)VALUES('"+miMarca.getIdMarca()+"','"+miMarca.getNombreMarca()+"')";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
}
