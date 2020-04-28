package controlador;

import java.sql.Connection;
import java.sql.SQLException;

import modelos.conexion.Conexion;
import modelos.conexion.ConexionFireBird;
import modelos.dao.Articulos;
import modelos.dao.Familias;
import modelos.dao.Grupos;
import modelos.dao.Marcas;
import modelos.dao.PuntosClietnes;

/**
 * @author Desarrollo
 *
 */
public class Coordinador {

	private Familias misFamilias;
	private Grupos misGrupos;
	private Articulos misArticulos;
	private Marcas misMarcas;
	private PuntosClietnes misPuntosClietnes;

	private ConexionFireBird miConexionFireBird;
	private Conexion miConexionMysql;

	public void iniciarServicio() {
		iniciarClases();
		iniciarCargaDatos();
	}

	private void iniciarClases() {
		misFamilias = new Familias();
		misGrupos = new Grupos();
		misMarcas = new Marcas();
		misArticulos = new Articulos();
		misPuntosClietnes = new PuntosClietnes();

		miConexionFireBird = new ConexionFireBird();
		miConexionMysql = new Conexion();

		misFamilias.setMiCoordinador(this);
		misGrupos.setMiCoordinador(this);
		misMarcas.setMiCoordinador(this);
		misArticulos.setMiCoordinador(this);
		misPuntosClietnes.setMiCoordinador(this);
	}

	private void iniciarCargaDatos() {
		try {
			misFamilias.consultar();
			misGrupos.consultar();
			misMarcas.consultar();
			misArticulos.consultar();
			misPuntosClietnes.consultar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectionFirebird() {
		return miConexionFireBird.getConnection();
	}

	public Connection getConnectionMysql() {
		return miConexionMysql.getConnection();
	}

}
