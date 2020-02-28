<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="GestionCuenta" method="post">
		<c:choose>
			<c:when test="${requestScope.ingresarRetirar=='ingresar'}">
				<h1>Ingresar</h1>
        		Introduce el monto que quieres inrgesar a tu cuenta:<br>
				<br>
				<input type="text" name="montoIngresar" id="opcion1">
				<br>
				<br>
				<input type="submit" name="opcion" value="montoIngresar">

				<br>
				<c:if test="${not empty requestScope.mensaje}">
					${requestScope.mensaje} 
				</c:if>
			</c:when>
			<c:otherwise>
				<h1>Retirar</h1>
				 Introduce el monto que quieres retirar de tu cuenta:<br>
				<br>
				<input type="text" name="montoRetirar" id="opcion2">
				<br>
				<br>
				<input type="submit" name="opcion" value="montoRetirar">
				
				
				<br>
				<c:if test="${not empty requestScope.mensaje}">
					${requestScope.mensaje}
				</c:if>
			</c:otherwise>
		</c:choose>
	</form>
<br><a href="MenuCuenta.jsp"><button>Volver</button></a>
	
	
</body>
</html>