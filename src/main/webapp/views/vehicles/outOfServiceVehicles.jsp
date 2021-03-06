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


	<h2>List of Out-Of-Service Vehicles...</h2>


	<table>
		<c:forEach items="${outServicelist}" var="item">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td><c:out value="${item.brand}" /></td>
				<td><c:out value="${item.model}" /></td>
				<td><c:out value="${item.plateNumber}" /></td>
				<td><c:out value="${item.seatQuantity}" /></td>
				<td><c:out value="${item.dailyPrice}" /></td>
				<td><c:out value="${item.status}" /></td>
				<td><c:out value="${item.type}" /></td>
				<td><c:out value="${item.fuelType}" /></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>