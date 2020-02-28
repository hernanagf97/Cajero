<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cajero</title>
</head>
<body>
	<form action="Login" method="post">
		<input type="text" name="idCuenta" placeholder="14"> <input
			type="submit" name="boton" value="Enviar">
	</form>
	<c:if test="${not empty requestScope.error }">
	${requestScope.error }
	</c:if>
</body>
</html>