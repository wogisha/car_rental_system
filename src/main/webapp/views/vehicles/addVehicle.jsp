<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Vehicle</title>

<h2>Add Vehicle</h2>
</head>
<body>
	<form action="/vehicles/add" method="post">

		brand: <input type="text" name="brand"> <br>
		model:  <input type="text" name="model"> <br> 	
		plateNumber: <input type="text" name="plateNumber"> <br> 
		
		seatQuantity: <input type="number" name="seatQuantity"> <br> 
		dailyPrice: <input type="number" name="dailyPrice"> <br>
	
		 Fuel Type: <select name = "fuelType">
		 <c:forEach var="state" items="${fuels}" >
   		 <option value="${state}">${state}</option>
		 </c:forEach>  
		 </select> <br>

         Vehicle Status: <select name = "status">
		 <c:forEach var="state1" items="${vehiclestatus}" >
   		 <option value="${state1}">${state1}</option>
		 </c:forEach>  
		 </select> <br>
		 
		  Vehicle Type: <select name = "type">
		 <c:forEach var="state2" items="${vehicletype}" >
   		 <option value="${state2}">${state2}</option>
		 </c:forEach>  
		 </select> <br>

	
	<input type="submit" value="submit"> <br>
	</form>
</body>
</html>