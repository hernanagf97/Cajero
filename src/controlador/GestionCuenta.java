package controlador;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import org.eclipse.persistence.internal.sessions.factories.model.session.ServerSessionConfig;

import modelo.beans.Cuenta;
import modelo.beans.Movimiento;
import modelo.daos.CuentaDAOImpl;
import modelo.daos.MovimientoDAOImpl;

/**
 * Servlet implementation class GestionCuenta
 */
@WebServlet("/GestionCuenta")
public class GestionCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionCuenta() {
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
		String opcion = "nada";
		try {
			opcion = request.getParameter("opcion");
		} catch (Exception e) {
			// TODO: handle exception
		}

		CuentaDAOImpl cuentaDAOImpl = new CuentaDAOImpl();
		MovimientoDAOImpl movimientoDAOImpl = new MovimientoDAOImpl();

		Cuenta cuenta = (Cuenta) request.getSession().getAttribute("objetoCuenta");

		switch (opcion) {
		case "ingresar":
			request.setAttribute("ingresarRetirar", "ingresar");
			request.getRequestDispatcher("ingresarRetirar.jsp").forward(request, response);
			break;
		case "retirar":
			request.setAttribute("ingresarRetirar", "retirar");
			request.getRequestDispatcher("ingresarRetirar.jsp").forward(request, response);
			break;
		case "movimientos":
			List<Movimiento> lista = movimientoDAOImpl.findPorCuenta(cuenta);
			request.setAttribute("movi", lista);
			request.getRequestDispatcher("Movimientos.jsp").forward(request, response);
			break;
		case "transferencia":
			request.getRequestDispatcher("Transferencia.jsp").forward(request, response);
			break;
		case "montoIngresar":
			try {
				double montoI = Double.parseDouble(request.getParameter("montoIngresar"));
				if (cuentaDAOImpl.anhadirMonto((Cuenta) cuenta, montoI)) {
					Movimiento movimiento = new Movimiento();
					movimiento.setCantidad(montoI);
					movimiento.setCuenta(cuenta);
					movimiento.setOperacion("Ingreso");
					movimiento.setFecha(new Date());
					movimientoDAOImpl.insert(movimiento);
					request.setAttribute("mensaje", "Se ha ingresado exitosamente");
				} else {
					request.setAttribute("mensaje", "No se pudo ingresar");
				}
				request.setAttribute("ingresarRetirar", "ingresar");
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "Monto ingresado incorrecto. Por favor introduzca un monto");
			}
			request.getRequestDispatcher("ingresarRetirar.jsp").forward(request, response);
			break;
		case "montoRetirar":
			try {
				double montoR = Double.parseDouble(request.getParameter("montoRetirar"));
				if (cuentaDAOImpl.retirarMonto((Cuenta) request.getSession().getAttribute("objetoCuenta"), montoR)) {

					Movimiento movimiento = new Movimiento();
					movimiento.setCantidad(montoR);
					movimiento.setCuenta(cuenta);
					movimiento.setOperacion("Retiro");
					movimiento.setFecha(new Date());
					movimientoDAOImpl.insert(movimiento);
					request.setAttribute("mensaje", "Se ha retirado exitosamente");
				} else {
					request.setAttribute("mensaje", "No se ha retirado exitosamente");
				}
				request.setAttribute("ingresarRetirar", "retirar");
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "Monto ingresado incorrecto. Por favor introduzca un monto");
			}
			request.getRequestDispatcher("ingresarRetirar.jsp").forward(request, response);
			break;
		case "Transferir":
			double montoTransferir = Double.parseDouble(request.getParameter("montoTransferir"));
			int numCuenta = Integer.parseInt(request.getParameter("cuentaTransferir"));
			Cuenta cuentaDestino = cuentaDAOImpl.findById(numCuenta);
			Cuenta cuentaOrigen = cuenta;

			if (cuentaDAOImpl.transferir(cuentaOrigen, cuentaDestino, montoTransferir) && cuentaDestino != cuenta) {

				Movimiento mov1 = new Movimiento();
				mov1.setCantidad(montoTransferir);
				mov1.setCuenta(cuentaOrigen);
				mov1.setOperacion("Transferencia realizada");
				mov1.setFecha(new Date());
				Movimiento mov2 = new Movimiento();
				mov2.setCantidad(montoTransferir);
				mov2.setCuenta(cuentaDestino);
				mov2.setOperacion("Transferencia recibida");
				mov2.setFecha(new Date());
				movimientoDAOImpl.insert(mov1);
				movimientoDAOImpl.insert(mov2);
				request.setAttribute("mensaje", "Transferencia exitosa");

			} else {
				request.setAttribute("mensaje", "Error al realizar la transferencia");
			}

			request.getRequestDispatcher("Transferencia.jsp").forward(request, response);

			break;
		case "cerrar":
			request.getSession().removeAttribute("usuario");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("MenuCuenta.jsp").forward(request, response);
			;
			break;

		}

	}

}
