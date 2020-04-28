package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Coordinador;
import modelos.vo.Familia;

public class Familias {
	
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
		String consulta = "SELECT fain_nom, fain_cod FROM familia_inventario";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		while (resultado.next()== true) {
			Familia miFamilia = new Familia();
			miFamilia.setIdFamilia(resultado.getString("fain_cod"));
			miFamilia.setNombreFamilia(resultado.getString("fain_nom"));
			registrar(miFamilia);
			actualizar(miFamilia);
		}
		resultado.close();
	}
	
	private void actualizar(Familia miFamilia) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="UPDATE familias SET nombreFamilia = '"+miFamilia.getNombreFamilia()+"' WHERE idFamilia ='"+miFamilia.getIdFamilia()+"'";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
	private void registrar(Familia miFamilia) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="INSERT IGNORE INTO familias(idFamilia,nombreFamilia)VALUES('"+miFamilia.getIdFamilia()+"','"+miFamilia.getNombreFamilia()+"')";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
}
