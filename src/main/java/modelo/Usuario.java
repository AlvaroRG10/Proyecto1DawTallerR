package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoUsuario;

/**
 * La clase Usuario representa a un usuario con su email y contraseña.
 * Proporciona metodos para gestionar el usuario en una base de datos,
 * incluyendo obtener, iniciar sesion, modificar, insertar y eliminar usuarios.
 */

public class Usuario {

	private String email;
	private String contrasena;

	/**
	 * Constructor para crear un usuario con email y contraseña especificos.
	 *
	 * @param email      El email del usuario.
	 * @param contrasena La contraseña del usuario.
	 */

	public Usuario(String email, String contrasena) {
		this.email = email;
		this.contrasena = contrasena;
	}

	/**
	 * Constructor vacio para crear un usuario sin inicializar sus atributos.
	 */

	public Usuario() {
	}

	/**
	 * Obtiene el email del usuario.
	 *
	 * @return El email del usuario.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email del usuario.
	 *
	 * @param email El nuevo email del usuario.
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 *
	 * @return La contraseña del usuario.
	 */

	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasena La nueva contraseña del usuario.
	 */

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Obtiene un usuario de la base de datos utilizando su email.
	 *
	 * @param email El email del usuario a obtener.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void obtenerUsuario(String email) throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.obtenerUsuario(email);

		this.setEmail(aux.getEmail());
		this.setContrasena(aux.getContrasena());
	}

	/**
	 * Realiza el inicio de sesion del usuario con la contraseña proporcionada.
	 *
	 * @param contrasena La contraseña proporcionada para el inicio de sesion.
	 * @return true si el inicio de sesion es exitoso, false en caso contrario.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public boolean login(String contrasena) throws SQLException {

		boolean ok = false;

		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.logeando(this, contrasena); // bd

		if (aux != null) {
			ok = true;
			this.setEmail(aux.getEmail());
			this.setContrasena(aux.getContrasena());
		}

		return ok;
	}

	/**
	 * Convierte el objeto Usuario a su representacion en formato JSON.
	 *
	 * @return Una cadena JSON que representa al usuario.
	 */

	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	/**
	 * Modifica la informacion del usuario en la base de datos.
	 *
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void modificar() throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		dao.modificar(this);
	}

	/**
	 * Inserta un nuevo usuario en la base de datos.
	 *
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void insertar() throws SQLException {

		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
	}

	/**
	 * Elimina el usuario de la base de datos.
	 *
	 * @param id El ID del usuario al eliminar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void eliminar(int id) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.eliminar(email);

	}

	/**
	 * Devuelve una representación en cadena del usuario.
	 *
	 * @return Una cadena que representa al usuario.
	 */

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contrasena=" + contrasena + "]";
	}

}