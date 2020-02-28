<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movimientos</title>
</head>
<body>
	<h1>Movimientos</h1>
	<strong>Cuenta: </strong> ${sessionScope.objetoCuenta.numeroCuenta}
	<br>
	<strong>Saldo: </strong> ${sessionScope.objetoCuenta.saldo}
	<br>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>Cantidad</th>
				<th>Fecha</th>
				<th>Tipo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.movi}" var="movimiento">
				<tr>
					<td>${movimiento.cantidad}</td>
					<td>${movimiento.fecha}</td>
					<td>${movimiento.operacion}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br>
	<a href="MenuCuenta.jsp"><button>Volver</button></a>
</body>
</html>