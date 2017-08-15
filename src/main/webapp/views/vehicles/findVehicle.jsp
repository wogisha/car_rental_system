<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Insert title here</title>
</head>
<body class="container">
<h2> Record for Vehicle # ${vehicleByid.id} : </h2> <br>
<div><a href="/vehicles/displayAllVehicles"
                                                                    class="btn btn-default"> Back</a>
    <c:if test="${vehicleByid.canBeReserved()}">
        <a href="/reservations/add?vehicleId=${vehicleByid.id}" class="btn btn-default">Reserve Car </a>
    </c:if>
</div>

<br/>
<table class="table table-stripped">
    <tr>
        <td><c:out value="${vehicleByid.brand}"/></td>
        <td><c:out value="${vehicleByid.model}"/></td>
        <td><c:out value="${vehicleByid.plateNumber}"/></td>
        <td><c:out value="${vehicleByid.seatQuantity}"/></td>
        <td><c:out value="${vehicleByid.dailyPrice}"/></td>
        <td><c:out value="${vehicleByid.status}"/></td>
        <td><c:out value="${vehicleByid.type}"/></td>
        <td><c:out value="${vehicleByid.fuelType}"/></td>
    </tr>
</table>


</body>
</html>