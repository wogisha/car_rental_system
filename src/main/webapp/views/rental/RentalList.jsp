<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rental List</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body class="container">

	<h3>Rental List</h3>

	<ul class="pagination center-block">
		<c:forEach var="i" begin="1" end="${rent.totalPages}">
			<li class="${page == i ? 'active':' '}"><a
				href="/rental?q=${param.q}&page=<c:out value = "${i}"/>"><c:out
						value="${i}" /> </a></li>
		</c:forEach>
	</ul>

	<a href="/" class="btn btn-default">Back Home</a>
	<table class="table table-striped">
		<tr>
			<th>Id</th>
			<th>Customer</th>
			<th>Employee</th>
			<th>Pick Up Date</th>
			<th>Drop Off Date</th>
			<th>Vehicle</th>
			<th>Status</th>
			<th>Total Paid</th>


		</tr>
		<c:forEach items="${rentals}" var="rent">
			<tr>
				<td><a href="/rental/<c:out value="${rent.id}"/>"><c:out
							value="${rent.id}" /></a></td>
				<td><c:out
						value="${rent.customer.fullName} - ${rent.customer.licenseNumber}" /></td>
				<td><c:out value="${rent.employee.fullName}" /></td>
				<td><c:out value="${rent.rentDate}" /></td>
				<td><c:out value="${rent.returnDate}" /></td>
				<td><c:out value="${rent.vehicle} / #${rent.vehicle.id}" /></td>
				<td><c:out value="${rent.rentStatus}" /></td>
				<td><c:out value="${rent.totalPaid}" /></td>

			</tr>
		</c:forEach>

	</table>


</body>
</html>