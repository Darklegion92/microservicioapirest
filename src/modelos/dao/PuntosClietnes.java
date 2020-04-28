package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Coordinador;
import modelos.vo.PuntosCliente;

public class PuntosClietnes {
	
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
		String consulta = "select sum(ptcl_db), p.ptcl_nit from puntos_cliente p group by p.ptcl_nit";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		int i = 0;
		while (resultado.next()== true) {
			i++;
			System.err.println(i);
			PuntosCliente miPuntosCliente= new PuntosCliente();
			miPuntosCliente.setAcumulado(resultado.getInt("sum"));
			miPuntosCliente.setDocumento(resultado.getString("ptcl_nit"));
			registrar(miPuntosCliente);
			actualizar(miPuntosCliente);
		}
		resultado.close();
	}

	private void actualizar(PuntosCliente miPuntosCliente) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="UPDATE puntosclientes " + 
				"SET acumulado = '" +miPuntosCliente.getAcumulado()+"'"+ 
				"WHERE documentoCliente = '"+miPuntosCliente.getDocumento()+"' and acumulado <> "+miPuntosCliente.getAcumulado()+"";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
	private void registrar(PuntosCliente miPuntosCliente) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="INSERT IGNORE INTO puntosclientes(documentoCliente,acumulado)VALUES('"+miPuntosCliente.getDocumento()+"','"+miPuntosCliente.getAcumulado()+"')";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
}
