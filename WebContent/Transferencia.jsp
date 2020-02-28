<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transferencia</title>
</head>
<body>
	<h1>Transferencia</h1>
	<form action="GestionCuenta?opcion=hacerTransferencia">
		Introduce la cantidad a transferir:<br> <input type="text"
			name="montoTransferir"><br> <br> Introduce el
		destinatario:<br> <input type="text" name="cuentaTransferir"><br>
		<br> <input type="submit" name="opcion" value="Transferir">
	</form>
	<br>
	<a href="MenuCuenta.jsp"><button>Volver</button></a>
	<br>
		<br>
	<c:if test="${not empty requestScope.mensaje}">
	${requestScope.mensaje}
	
	</c:if>

	
</body>
</html>