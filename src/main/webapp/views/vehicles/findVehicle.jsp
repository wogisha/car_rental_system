<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> Record for Vehicle # ${vehicleByid.id} : </h2> <br>

	<table>
			<tr>
				<td><c:out value="${vehicleByid.brand}" /></td>
				<td><c:out value="${vehicleByid.model}" /></td>
				<td><c:out value="${vehicleByid.plateNumber}" /></td>
				<td><c:out value="${vehicleByid.seatQuantity}" /></td>
				<td><c:out value="${vehicleByid.dailyPrice}" /></td>
				<td><c:out value="${vehicleByid.status}" /></td>
				<td><c:out value="${vehicleByid.type}" /></td>
				<td><c:out value="${vehicleByid.fuelType}" /></td>
			</tr>
	</table>


</body>
</html>