package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Cita;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCita;

/**
 * Servlet implementation class Listar
 */
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Listar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int opcion = Integer.parseInt(request.getParameter("op"));

		if (opcion == 1) {

			int id = Integer.parseInt(request.getParameter("id"));

			String respuestaJSON;
			try {
				respuestaJSON = DaoCita.getInstance().dameJsonuno(id);
				System.out.println(respuestaJSON);

				PrintWriter respuesta = response.getWriter();

				respuesta.print(respuestaJSON);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcion == 0) {

			String respuestaJSON;
			try {
				respuestaJSON = DaoCita.getInstance().dameJson();
				System.out.println(respuestaJSON);

				PrintWriter respuesta = response.getWriter();

				respuesta.print(respuestaJSON);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
