<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation Information</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="<spring:url value="/resources/static/js/CheckValidDate.js"/>"></script>

</head>
<body class="container">
	<h2 align="center">Confirmation</h2>
	<br>
	<br>
	
<script type="text/javascript">
	$(window).load(function() {
		$('rentDate').glDatePicker();
	});
</script>
	<form:form action="confirmation" method="post" modelAttribute="rent">

		<div class="col-sm-6">
			<div class="form-group">
				<form:label path="customer.fullName"> Driver Name: </form:label>
				<form:label path="customer.fullName">${sessionScope.rent.customer.fullName}</form:label>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="licenseNumber"> License Number: </label>
				<form:label path="customer.licenseNumber">${sessionScope.rent.customer.licenseNumber} </form:label>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="form-group">
				<label for="brand"> Rental Partner: </label> <label>${sessionScope.rent.reservation.vehicle.brand}
				</label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="model"> Car Type: </label> <label>${sessionScope.rent.reservation.vehicle.model}
				</label>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="rentDate"> Pick-Up Date: </label> 
				<form:input path="rentDate" class="datepicker" id="rentDate" type="date"/>
				<form:errors path="rentDate" cssStyle="color : red;" />
				<%-- <form:input type="date" name="rentDate" class="form-control"
					path="rentDate" />
				<c:if test="${rentDate.value == null}">
					<div class="alert alert-danger">You must choose a date to
						pick up the car!</div>
				</c:if> --%>
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="returnDate"> Drop-Off Date: </label>
				<form:input path="returnDate" class="datepicker" id="returnDate" type="date" />
				<form:errors path="returnDate" cssStyle="color : red;" />
				<%-- <form:input type="date" name="returnDate" class="form-control"
					path="returnDate" /> --%>
			</div>
		</div>

		<div class="col-xs-12">
			<button type="submit" class="btn btn-primary">Confirm</button>
			<div class="col-xs-2">
				<a href="/" class="btn btn-primary">Back Home</a>
			</div>
		</div>

	</form:form>
</body>
</html>