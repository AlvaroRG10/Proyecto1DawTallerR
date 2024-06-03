package dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.Usuario;

/**
 * Esta clase proporciona metodos para acceder a la base de datos y realizar
 * operaciones relacionadas con los usuarios.
 */

public class DaoUsuario {

	private Connection con;

	/**
	 * Constructor de la clase DaoUsuario. Establece una conexion con la base de
	 * datos.
	 * 
	 * @throws SQLException si ocurre un error al intentar establecer la conexión
	 *                      con la base de datos.
	 */

	public DaoUsuario() throws SQLException {
		this.con = DBConexion.getConexion();
	}

	/**
	 * Inserta un nuevo usuario en la base de datos.
	 * 
	 * @param usuario el usuario a ser insertado.
	 * @throws SQLException si ocurre un error de acceso a la base de datos.
	 */

	public void insertar(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario (email, contrasena) VALUES (?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getContrasena());
			ps.executeUpdate();
		}
	}

	/**
	 * Modificar un usuario en la base de datos.
	 * 
	 * @param usuario el usuario al ser modificado.
	 * @throws SQLException
	 */

	public void modificar(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuario SET contrasena = ? WHERE email = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuario.getContrasena());
			ps.setString(2, usuario.getEmail());
			ps.executeUpdate();
		}
	}

	/**
	 * Eliminar un usuario en la base de datos.
	 * 
	 * 
	 * @param email del usuario al ser eliminado.
	 * @throws SQLException
	 */

	public void eliminar(String email) throws SQLException {
		String sql = "DELETE FROM usuario WHERE email = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.executeUpdate();
		}
	}

	/**
	 * Obtiene un usuario de la base de datos dado su correo electronico.
	 * 
	 * @param email el correo electronico del usuario a ser obtenido.
	 * @return el objeto Usuario si se encuentra en la base de datos, de lo
	 *         contrario null.
	 */

	public Usuario obtenerUsuario(String email) {
		String sql = "SELECT * FROM usuario WHERE email = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getString("email"), rs.getString("contrasena"));
			}
		} catch (SQLException ex) {
			System.err.println("Error en el acceso a la BD: " + ex.getMessage());
		}
		return null;
	}

	public Usuario logeando(Usuario u, String contrasena) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE email = ? AND contrasena=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, u.getEmail());
			ps.setString(2, contrasena);

			ResultSet rs = ps.executeQuery();

			Usuario aux = null;
			if (rs.next()) {

				aux = new Usuario(rs.getString("email"), rs.getString("contrasena"));
			}

			return aux;

		}

	}

	public ArrayList<Usuario> obtenerUsuarios() throws SQLException {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getString("email"), rs.getString("contrasena"));
				usuarios.add(usuario);
			}
		}
		return usuarios;
	}
}
