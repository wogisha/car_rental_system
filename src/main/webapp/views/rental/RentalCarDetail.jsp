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
		modelAttribute="rent" class ="container">

		<div class="col-sm-6">
			<div class="form-group">
				<label for="fullName"> Driver Name: </label>
				<form:label path="customer.fullName">${rent.customer.fullName}</form:label>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="licenseNumber"> License Number </label>
				<form:label path="customer.licenseNumber">${rent.customer.licenseNumber}</form:label>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="brand"> Rental Partner </label> <label>${rent.reservation.vehicle.brand}
				</label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="model"> Car Type </label>
				<label>${rent.reservation.vehicle.model} </label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="rentDate"> Pick-Up Date </label>
				<form:label path="rentDate">${rent.rentDate} </form:label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="returnDate"> Drop-Off Date </label>
				<form:label path="returnDate">${rent.returnDate} </form:label>	
			</div>
		</div>

		<div class="col-xs-6">
			<a href="/" class="btn btn-primary">Back Home</a>
		</div>


		<c:if test="${rent.isRented()}">
			<div class="col-xs-6">
				<button type="submit" class="btn btn-primary">Return Car</button>
			</div>
		</c:if>
	</form:form>
</body>
</html>