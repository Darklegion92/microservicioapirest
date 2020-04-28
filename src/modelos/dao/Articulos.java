package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controlador.Coordinador;
import modelos.vo.Articulo;

public class Articulos {
	
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
		Date fecha = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String consulta = "select a.arti_cod, a.arti_des, a.fain_cod, a.marc_cod, a.grin_cod, "
				+ "p.prar_fijo, p.prar_dto1 from articulo a, precios_articulo p "
				+ "where p.arti_cod = a.arti_cod and p.lipr_cod = 1 and arti_fecha>='"+df.format(fecha)+"'";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		while (resultado.next()== true) {
			Articulo miArticulo = new Articulo();
			miArticulo.setIdFamilia(resultado.getString("fain_cod"));
			miArticulo.setCantidad(0);
			miArticulo.setCodigo(resultado.getString("arti_cod").replaceAll("'", ""));
			miArticulo.setDescuento(resultado.getInt("prar_dto1"));
			miArticulo.setIdFamilia(resultado.getString("fain_cod"));
			miArticulo.setIdGrupo(resultado.getString("grin_cod"));
			miArticulo.setIdMarca(resultado.getString("marc_cod"));
			miArticulo.setNombre(resultado.getString("arti_des").replaceAll("'", ""));
			miArticulo.setPrecio(resultado.getDouble("prar_fijo"));
			registrar(miArticulo);
			actualizar(miArticulo);
		}
		resultado.close();
	}
	
	private void actualizar(Articulo miArticulo) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="UPDATE articulos " + 
				"SET " + 
				"nombreArticulo ='"+miArticulo.getNombre()+"', "+ 
				"precioArticulo ="+miArticulo.getPrecio()+", " +  
				"descuentoArticulo`="+miArticulo.getDescuento()+", " + 
				"cantidadArticulo = "+miArticulo.getCantidad()+", " + 
				"idMarca = '"+miArticulo.getIdMarca()+"', " + 
				"idFamilia = '"+miArticulo.getIdFamilia()+"'', " + 
				"idGrupo = '"+miArticulo.getIdGrupo()+"' "+ 
				"WHERE `idarticulo` = '"+miArticulo.getCodigo()+"'";
		statement=connection.prepareStatement(consulta);
		System.err.println(consulta);
		statement.execute();
	}
	
	private void registrar(Articulo miArticulo) throws SQLException {
		Connection connection=miCoordinador.getConnectionMysql(); //instancio la clase conexion
		PreparedStatement statement = null;
		String consulta="INSERT IGNORE INTO articulos(idArticulo,nombreArticulo, precioArticulo,descuentoArticulo,cantidadArticulo,idFamilia,idGrupo,idMarca)VALUES('"
		+miArticulo.getCodigo()+"','"+miArticulo.getNombre()+"','"+miArticulo.getPrecio()+"','"+miArticulo.getDescuento()+"','"+miArticulo.getCantidad()+"','"+miArticulo.getIdFamilia()+"','"
				+miArticulo.getIdGrupo()+"','"+miArticulo.getIdMarca()+"')";
		statement=connection.prepareStatement(consulta);
		statement.execute();
	}
	
}
