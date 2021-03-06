<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body class="container">
<h2 align="center">PAYMENT</h2>
<br><br>
	<form:form action="payment" method="post" modelAttribute="rent">
		<div class="col-sm-6">
			<div class="form-group">
				<label > Driver Name: </label>
				<form:label path="customer.fullName">${rent.customer.fullName}</form:label>
			</div>
		</div>
		
		<div class="col-sm-6">
			<div class="form-group">
				<label for="licenseNumber"> License Number: </label>
				<form:label path="customer.licenseNumber">${rent.customer.licenseNumber}</form:label>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="brand"> Rental Partner: </label>
				<form:label path="reservation.vehicle.brand">${rent.reservation.vehicle.brand}</form:label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="model"> Car Type: </label>
				<form:label path="reservation.vehicle.model">${rent.reservation.vehicle.model}</form:label>
			</div>
		</div>
		 
		<div class="col-xs-6">
			<div class="form-group">
				<label for="rentDate"> Pick-Up Date: </label>
				<form:label path="rentDate">${rent.rentDate} </form:label>	
			</div>
		</div>

		 <div class="col-xs-6">
			<div class="form-group">
				<label for="returnDate"> Drop-Off Date: </label>
				<form:label path="returnDate">${rent.returnDate} </form:label>	
			</div>
		</div>
		
		<div class="col-xs-6">
			<div class="form-group">
				<label for="totalPaid"> Total Paid: </label>
				<form:label path="totalPaid">${rent.totalPaid} </form:label>	
			</div>
		</div>

		<div class="col-xs-12"><button type="submit" class="btn btn-primary">Payment</button>
		<div class="col-xs-2"><a href="/" class="btn btn-primary">Back Home</a></div></div>
		
	</form:form>
</body>
</html>