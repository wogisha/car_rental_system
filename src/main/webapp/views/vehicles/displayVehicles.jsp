<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<title>Insert title here</title>
</head>
<body class="container"><br/>
	<h2>List of Vehicles...</h2>

	<a href="/vehicles/add " class="btn btn-default">add Vehicle</a> <a href="/ " class="btn-default btn">Back Home </a>
	<br/>
	<table class="table table-stripped">
		<c:forEach items="${listofVehicles}" var="item">
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