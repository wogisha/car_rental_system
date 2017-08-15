<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
	<form:form action="confirmation" method="post" modelAttribute="rent">

		<div class="col-sm-6">
			<div class="form-group">
				<form:label path="customer.fullName"> Driver Name: </form:label>
				<form:label path="customer.fullName">${sessionScope.rent.customer.fullName}</form:label>
			</div>
		</div>
		
		<div class="col-sm-6">
			<div class="form-group">
				<label for="licenseNumber"> License Number </label>
				<form:label path="customer.licenseNumber">${sessionScope.rent.customer.licenseNumber} </form:label>
			</div>
		</div>

		<%-- <div class="col-sm-6">
			<div class="form-group">
				<label for="brand"> Rental Partner </label>
				<label>${sessionScope.rent.reservation.vehicle.brand} </label>	
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="model"> Car Type </label>
				<label>${sessionScope.rent.reservation.vehicle.model} </label>	
			</div>
		</div> --%>
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
				<form:label path="dailyRentFee">${sessionScope.rent.dailyRentFee} </form:label>	
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Confirm</button>
		

	</form:form>
</body>
</html>