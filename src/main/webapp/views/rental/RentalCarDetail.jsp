<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check In Car</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<h2 Align="center">RENTAL CAR DETAIL</h2>
	<br>
	<br>
	<form:form action="/rental/updaterentalcar/${rent.id}" method="post"
		modelAttribute="rent">

		<div class="col-sm-6">
			<div class="form-group">
				<label for="fullName"> Driver Name </label>
				<form:input name="fullName" class="form-control"
					path="customer.fullName" />

				<%-- <select name="customerId">
					<c:forEach items="${customers}" var="customer">
						<option value="${customer.id}">${customer.fullName} - ${customer.licenseNumber}</option>
					</c:forEach>

				</select> --%>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="licenseNumber"> License Number </label>
				<form:input name="licenseNumber" class="form-control"
					path="customer.licenseNumber" />
			</div>
		</div>

		<div class="col-sm-6">
			<label for="brand"> Rental Partner </label>
			<form:input name="brand" class="form-control"
				path="reservation.vehicle.brand" />
			<div class="form-group"></div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="model"> Car Type </label>
				<form:input name="model" class="form-control"
					path="reservation.vehicle.model" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="rentDate"> Pick-Up Date </label>
				<form:input type="date" name="rentDate" class="form-control"
					path="rentDate" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="returnDate"> Drop-Off Date </label>
				<form:input type="date" name="returnDate" class="form-control"
					path="returnDate" />
			</div>
		</div>

		<div class="col-xs-6">
				<a href="/" class="btn btn-primary">Back Home</a></div>

		<c:if test="${rentalService.returnedCar(rent)}">
			<div class="col-xs-6">
			<button type="submit" class="btn btn-primary">Return Car</button>			
			</div>
		</c:if>
	</form:form>
</body>
</html>