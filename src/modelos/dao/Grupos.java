package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.Coordinador;
import modelos.vo.Grupo;

public class Grupos {
	
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
		String consulta = "SELECT grin_nom, fain_cod,grin_cod FROM grupo_inventario";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		while (resultado.next()== true) {
			Grupo miGrupo = new Grupo();
			miGrupo.setIdFamilia(resultado.getString("fain_cod"));
			miGrupo.setNombreGrupo(resultado.getString("grin_nom"));
			miGrupo.setIdGrupo(resultado.getString("grin_cod"));
			registrar(miGrupo);
			actualizar(miGrupo);
		}
		statement.close();
		
	}
	
	private void actualizar(Grupo miGrupo) throws SQLException {
		Connection connection= miCoordinador.getConnectionMysql();
		PreparedStatement statement = null;
		String consulta="UPDATE grupos SET `nombreGrupo` = '"+miGrupo.getNombreGrupo()+"', `idFamilia` = '"+miGrupo.getIdFamilia()+"' " + 
				"WHERE `idgrupo` = '"+miGrupo.getIdGrupo()+"';";
		statement=connection.prepareStatement(consulta);
		statement.execute();
		statement.close();
	}

	private void registrar(Grupo miGrupo) throws SQLException {
		Connection connection= miCoordinador.getConnectionMysql();
		PreparedStatement statement = null;
		String consulta="INSERT IGNORE INTO grupos(idGrupo,nombreGrupo,idFamilia)VALUES('"+miGrupo.getIdGrupo()+"','"
		+miGrupo.getNombreGrupo()+"','"+miGrupo.getIdFamilia()+"')";
		statement=connection.prepareStatement(consulta);
		statement.execute();
		statement.close();
	}
	
}
