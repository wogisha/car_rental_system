<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
	<h2>Check In Car</h2>
	<form:form action="checkincar" method="post" modelAttribute="rent">

		<div class="col-sm-6">
			<div class="form-group">
				<label for="fullName"> Driver Name </label>
				<form:input name="fullName" class="form-control"
					path="customer.fullName" />
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
			<%-- <div class="dropdown">
				<form:input name="brand" class="form-control"
					path="reservation.vehicle.brand" />
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown" path="reservation.vehicle.brand">
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">HTML</a></li>
					<li><a href="#">CSS</a></li>
					<li><a href="#">JavaScript</a></li>
				</ul> --%>
			</div>
			<%-- <div class="form-group">
				
				
			</div> --%>
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
			<div class="form-group">
				<label for="dailyRentFee"> Daily Fee </label>
				<form:input type="number" name="dailyRentFee" class="form-control"
					path="dailyRentFee" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="fuelProvidedBy"> Fuel Provider </label>
				<form:input name="fuelProvidedBy" class="form-control"
					path="fuelProvidedBy" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="fuelCharge"> Fuel Charge </label>
				<form:input type="number" name="fuelCharge" class="form-control"
					path="fuelCharge" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="fuelProvidedBy"> Fuel Provider </label>
				<form:input name="fuelProvidedBy" class="form-control"
					path="fuelProvidedBy" />
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Check In</button>


	</form:form>
</body>
</html>