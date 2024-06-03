package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase DBConexion proporciona una conexion a la base de datos MySQL.
 * Utiliza el patron Singleton para asegurar que solo se crea una instancia de
 * la conexion.
 */

public class DBConexion {

	/**
	 * La URL del JDBC para conectarse a la base de datos MySQL.
	 */

	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/taller";

	/**
	 * La instancia de conexion unica (Singleton).
	 */

	public static Connection instance = null;

	/**
	 * Obtiene la conexion a la base de datos. Si la conexion no existe, la crea con
	 * las propiedades especificadas.
	 *
	 * @return la instancia unica de la conexión a la base de datos.
	 * @throws SQLException si ocurre un error al conectar con la base de datos.
	 */

	public static Connection getConexion() throws SQLException {

		if (instance == null) {
			// opcional
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");

			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		return instance;

	}

}
