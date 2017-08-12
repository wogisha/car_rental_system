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
	<form:form action="checkInCar" method="post" modelAttribute="rent">

		<div class="col-sm-6">
			<div class="form-group">
				<label for="fullName"> Customer Name </label>
				<form:input name="fullName" class="form-control"
					path="customer.fullName" />
			</div>
		</div>

		<div class="col-xs-6">
			<div class="form-group">
				<label for="rentDate"> Rent Date </label>

				<form:input type="date" name="rentDate" class="form-control"
					path="rentDate" />
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Check In</button>
		<!-- <table border="1">   -->
		<!--     <tr> -->
		<!--     	<td class="title">Rental Partner</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Car Type</td> -->
		<!--     	<td></td> -->
		<!--     </tr>   -->
		<!--     <tr> -->
		<!--     	<td class="title">Pick-Up</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Drop-Off</td> -->
		<!--     	<td></td> -->
		<!--     </tr>  -->
		<!--     <tr> -->
		<!--     	<td class="title">Pick-Up Location</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Drop-Off Location</td> -->
		<!--     	<td></td> -->
		<!--     </tr> -->
		<!--     <tr> -->
		<!--     	<td class="title">Driver</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Daily Fee</td> -->
		<!--     	<td></td> -->
		<!--     </tr> -->
		<!--     <tr> -->
		<!--     	<td class="title">Fuel Provider</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Fuel Charge</td> -->
		<!--     	<td></td> -->
		<!--     </tr> -->
		<!--     <tr> -->
		<!--     	<td class="title">Total Paid</td> -->
		<!--     	<td></td> -->
		<!--     	<td class="title">Taxes and fee</td> -->
		<!--     	<td></td>    	 -->
		<!--     </tr> -->
		<!--     <tr> -->
		<!--     	<td class="title">Amount Due At Pick Up</td> -->
		<!--     	<td></td> -->
		<!--     	<td><input type="button" class="btn btn-success" value="Submit"></td> -->
		<!--     </tr> -->

		<%--    <%--  <c:forEach items="${students}" var="bean"> --%>
		<%--         <tr> --%>
		<%--             <td>${bean.name }</td> --%>
		<%--             <td>${bean.lastName }</td> --%>
		<%--             <td><input type="button" name="edit" value="Do!" --%>
		<%--                 onclick="foo();" /></td> --%>
		<%--             <td><form action="<%="EditStudent?studentid=6" %>"><input type="submit" value="Edit" /></form></td> --%>
		<%--         </tr> --%>
		<%--     </c:forEach> --%>
		<!-- </table> -->

	</form:form>
</body>
</html>