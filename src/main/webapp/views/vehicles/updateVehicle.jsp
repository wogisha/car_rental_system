
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Update Vehicle...</h2>

	<form:form modelAttribute="vehicle" action="/vehicles/update"
		method="POST">
	
		Brand:<tr>
			<td><form:input path="brand" /></td>
			<br>
		</tr>
		Model:<tr>
			<td><form:input path="model" /></td>
			<br>
		</tr>
 Plate Number:<tr>
			<td><form:input path="plateNumber" /></td>
			<br>
		</tr>
 Seat Quantity:<tr>
			<td><form:input path="seatQuantity" type="number" /></td>
			<br>
		</tr>
Daily Price:<tr>
			<td><form:input path="dailyPrice" /> <br> 
			<form:errors path="dailyPrice" /></td>
		</tr>
  Status:<tr>
			<td><form:select path="status" items="${vehiclestatus1}" /></td>
			<br>
		</tr>
	Type: <tr>
			<td><form:select path="type" items="${vehicletype1}" /></td>
			<br>
		</tr>
Fuel Type:<tr>
			<td><form:select path="fuelType" items="${fuels1}"/></td>
			<br>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="updateVehicle" /></td>
		</tr>

	</form:form>

</body>
</html>