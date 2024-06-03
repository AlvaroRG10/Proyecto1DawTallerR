package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoCita;

/**
 * La clase Cita representa una cita con detalles como nombre, email, marca,
 * modelo, matricula y fecha. Proporciona metodos para gestionar la cita en una
 * base de datos, incluyendo insertar, actualizar, borrar y obtener citas.
 */

public class Cita {

	private int idcita;
	private String nombre;
	private String email;
	private String marca;
	private String modelo;
	private String matricula;
	private String fecha;

	/**
	 * Constructor vacio para crear una cita sin inicializar sus atributos.
	 */

	public Cita() {

	}

	/**
	 * Constructor para crear una cita sin ID.
	 *
	 * @param nombre    El nombre de la persona.
	 * @param email     El email de la persona.
	 * @param marca     La marca del vehículo.
	 * @param modelo    El modelo del vehículo.
	 * @param matricula La matrícula del vehículo.
	 * @param fecha     La fecha de la cita.
	 */

	public Cita(String nombre, String email, String marca, String modelo, String matricula, String fecha) {

		this.nombre = nombre;
		this.email = email;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.fecha = fecha;
	}

	/**
	 * Constructor para crear una cita con ID.
	 *
	 * @param idcita    El ID de la cita.
	 * @param nombre    El nombre de la persona.
	 * @param email     El email de la persona.
	 * @param marca     La marca del vehículo.
	 * @param modelo    El modelo del vehículo.
	 * @param matricula La matrícula del vehículo.
	 * @param fecha     La fecha de la cita.
	 */

	public Cita(int idcita, String nombre, String email, String marca, String modelo, String matricula, String fecha) {

		this.idcita = idcita;
		this.nombre = nombre;
		this.email = email;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.fecha = fecha;
	}

	/**
	 * Obtiene el ID de la cita.
	 *
	 * @return El ID de la cita.
	 */

	public int getIdcita() {
		return idcita;
	}

	/**
	 * Establece el ID de la cita.
	 *
	 * @param idcita El nuevo ID de la cita.
	 */

	public void setIdcita(int idcita) {
		this.idcita = idcita;
	}

	/**
	 * Obtiene el nombre de la persona.
	 *
	 * @return El nombre de la persona.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la persona.
	 *
	 * @param nombre El nuevo nombre de la persona.
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el email de la persona.
	 *
	 * @return El email de la persona.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email de la persona.
	 *
	 * @param email El nuevo email de la persona.
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la marca del vehiculo.
	 *
	 * @return La marca del vehiculo.
	 */

	public String getMarca() {
		return marca;
	}

	/**
	 * Establece la marca del vehiculo.
	 *
	 * @param marca La nueva marca del vehiculo.
	 */

	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Obtiene el modelo del vehiculo.
	 *
	 * @return El modelo del vehiculo.
	 */

	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo del vehiculo.
	 *
	 * @param modelo El nuevo modelo del vehiculo.
	 */

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Obtiene la matricula del vehiculo.
	 *
	 * @return La matricula del vehiculo.
	 */

	public String getMatricula() {
		return matricula;
	}

	/**
	 * Establece la matricula del vehiculo.
	 *
	 * @param matricula La nueva matricula del vehiculo.
	 */

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Obtiene la fecha de la cita.
	 *
	 * @return La fecha de la cita.
	 */

	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de la cita.
	 *
	 * @param fecha La nueva fecha de la cita.
	 */

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Inserta una nueva cita en la base de datos.
	 *
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void insertar() throws SQLException {

		DaoCita dao = new DaoCita();
		dao.insertar(this);

		DaoCita.getInstance().Update(this);

	}

	/**
	 * Convierte el objeto Cita a su representacion en formato JSON.
	 *
	 * @return Una cadena JSON que representa la cita.
	 */

	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	/**
	 * Actualiza la informacion de la cita en la base de datos.
	 *
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void actualizar() throws SQLException {

		DaoCita dao = new DaoCita();
		dao.actualizar(this);
	}

	/**
	 * Borra la cita de la base de datos.
	 *
	 * @param id El ID de la cita al borrar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void borrar(int id) throws SQLException {
		DaoCita dao = new DaoCita();
		dao.borrar(id);

	}

	/**
	 * Obtiene una cita de la base de datos utilizando su ID.
	 *
	 * @param id El ID de la cita al obtener.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */

	public void obtenerPorId(int id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Devuelve una representacion en cadena de la cita.
	 *
	 * @return Una cadena que representa la cita.
	 */

	@Override
	public String toString() {
		return "Cita [idcita=" + idcita + ", nombre=" + nombre + ", email=" + email + ", marca=" + marca + ", modelo="
				+ modelo + ", matricula=" + matricula + ", fecha=" + fecha + "]";
	}
}
