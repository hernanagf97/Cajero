package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Cuenta;
import modelo.daos.CuentaDAOImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cuenta cuenta = null;
		CuentaDAOImpl cuentaDAOImpl = new CuentaDAOImpl();
		int numCuenta = 0;
		try {
			numCuenta = Integer.valueOf(request.getParameter("idCuenta"));
			cuenta = cuentaDAOImpl.findById(numCuenta);
			if (cuenta != null) {
				request.getSession().setAttribute("objetoCuenta", cuenta);
				request.getRequestDispatcher("MenuCuenta.jsp").forward(request, response);
			} else {
				System.out.println("errorrrr");
				request.setAttribute("error", "Error, la cuenta no existe");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception

			request.setAttribute("error", "Ha habido un error al intentar ingresar");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
