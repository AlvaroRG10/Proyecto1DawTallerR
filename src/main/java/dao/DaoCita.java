package dao;

import java.sql.*;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Cita;

/**
 * Esta clase proporciona metodos de acceso a datos para administrar citas en la
 * base de datos.
 */

public class DaoCita {
	private Connection con = null;
	private static DaoCita instance = null;

	/**
	 * Constructor de la clase DaoCita. Se establece una conexión con la base de
	 * datos.
	 * 
	 * @throws SQLException si ocurre un error al establecer la conexión.
	 */

	public DaoCita() throws SQLException {
		this.con = DBConexion.getConexion();
	}

	/**
	 * Este metodo es que utilizo para aplicar el patron Singelton.
	 * 
	 * @return
	 * @throws SQLException
	 */

	public static DaoCita getInstance() throws SQLException {

		if (instance == null) {
			instance = new DaoCita();
		}

		return instance;
	}

	/**
	 * Metodo de insercion en la bd del objeto cita
	 * 
	 * @param u Objeto tipo citas
	 * @throws SQLException
	 */
	public void insertar(Cita cita) throws SQLException {
		String sql = "INSERT cita (idcita, nombre, email, marca, modelo, matricula, fecha) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cita.getIdcita());
			ps.setString(2, cita.getNombre());
			ps.setString(3, cita.getEmail());
			ps.setString(4, cita.getMarca());
			ps.setString(5, cita.getModelo());
			ps.setString(6, cita.getMatricula());
			ps.setString(7, cita.getFecha());
			ps.executeUpdate();
		}
	}

	/**
	 * Metodo de actualizar en la bd del objeto cita.
	 * 
	 * @param cita
	 * @throws SQLException
	 */

	public void actualizar(Cita cita) throws SQLException {
		String sql = "UPDATE cita SET nombre = ?, email = ?, marca = ?, modelo = ?, matricula = ?, fecha = ? WHERE idcita = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cita.getNombre());
			ps.setString(2, cita.getEmail());
			ps.setString(3, cita.getMarca());
			ps.setString(4, cita.getModelo());
			ps.setString(5, cita.getMatricula());
			ps.setString(6, cita.getFecha());
			ps.setInt(7, cita.getIdcita());
			ps.executeUpdate();
		}
	}

	/**
	 * Metodo de borrar en la bd de objeto cita.
	 * 
	 * @param idcita
	 * @throws SQLException
	 */

	public void borrar(int idcita) throws SQLException {
		String sql = "DELETE FROM cita WHERE idcita = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idcita);
			ps.executeUpdate();
		}
	}

	public void Update(Cita a) {

	}

	/**
	 * Recupera una lista de todas las citas de la base de datos.
	 * 
	 * @return un ArrayList de objetos Cita que representan las citas.
	 * @throws SQLException si ocurre un error de acceso a la base de datos.
	 */
	public ArrayList<Cita> listar() throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM cita ");
		ResultSet rs = ps.executeQuery();

		ArrayList<Cita> result = null;

		while (rs.next()) {

			if (result == null) {
				result = new ArrayList<>();

			}

			result.add(new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7)));

		}

		return result;

	}

	/**
	 * Recupera una cita de la base de datos utilizando su ID.
	 * 
	 * @param id El ID de la cita al recuperar.
	 * @return Un objeto Cita que representa la cita recuperada.
	 * @throws SQLException si ocurre un error de acceso a la base de datos.
	 */

	public Cita obtenerPorID(int id) throws SQLException {

		String sql = "SELECT * FROM cita WHERE idcita=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Cita c = new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7));

		return c;
	}

	/**
	 * Convierte la lista de todas las citas a una cadena JSON.
	 * 
	 * @return Una cadena JSON que representa la lista de citas.
	 * @throws SQLException si ocurre un error de acceso a la base de datos.
	 */

	public String dameJson() throws SQLException {

		String json = "";
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}

	/**
	 * Convierte una cita específica a una cadena JSON.
	 * 
	 * @param id El ID de la cita al convertir.
	 * @return Una cadena JSON que representa la cita.
	 * @throws SQLException si ocurre un error de acceso a la base de datos.
	 */

	public String dameJsonuno(int id) throws SQLException {

		String json = "";
		Gson gson = new Gson();

		json = gson.toJson(this.obtenerPorID(id));

		return json;

	}

	/**
	 * Método placeholder para listar citas en formato JSON basado en un tipo de
	 * cita.
	 * 
	 * @param tipoCita El tipo de cita al listar.
	 * @return null actualmente.
	 */

	public static char[] listarJson(int tipoCita) {
		// TODO Auto-generated method stub
		return null;
	}

}
