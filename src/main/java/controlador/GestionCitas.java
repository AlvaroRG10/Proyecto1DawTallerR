package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cita;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoCita;

/**
 * Servlet implementation class GestionCitas
 */
public class GestionCitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionCitas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		sesion = request.getSession();
		
		String emailSesion = (String) sesion.getAttribute("email");

		
		
		
		PrintWriter out = response.getWriter();

		int opcion = Integer.parseInt(request.getParameter("op"));

		if (opcion == 2) {

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DaoCita cita = new DaoCita();
				cita.borrar(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String matricula = request.getParameter("matricula");
		String fecha = request.getParameter("fecha");

		Cita a = new Cita(nombre, email, marca, modelo, matricula, fecha);
		try {
			a.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error de conexion");
		}

		System.out.println(a.toString());

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String matricula = request.getParameter("matricula");
		String fecha = request.getParameter("fecha");

		Cita c = new Cita(id, nombre, email, marca, modelo, matricula, fecha);

		try {
			c.actualizar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
