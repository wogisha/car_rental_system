<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Insert title here</title>
</head>
<body class="container"><br/>
<h2>List of Vehicles...</h2>
<p>${updatedVehicleMessage}</p>
<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
<a href="/vehicles/add" class="btn btn-default">add Vehicle</a>
</sec:authorize>
<a href="/vehicles/FuelTypePetrol " class="btn-default btn"> View Petrol Only cars </a>
<a href="/vehicles/FuelTypeDiesel    " class="btn-default btn"> View Diesel Only cars </a>
<a href="/ " class="btn-default btn">Back Home </a>
<br/>
<table class="table table-stripped">
    <tr>
        <th>#</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Plate Number</th>
        <th>Seats</th>
        <th>Daily Cost</th>
        <th>Status</th>
        <th>Type</th>
        <th>Fuel type</th>
        <sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
            <th></th>
        </sec:authorize>
    </tr>
    <c:forEach items="${listofVehicles}" var="item">
        <tr>
            <td><a href="/vehicles/view/${item.id}"><c:out value="${item.id}"/></a></td>
            <td><c:out value="${item.brand}"/></td>
            <td><c:out value="${item.model}"/></td>
            <td><c:out value="${item.plateNumber}"/></td>
            <td><c:out value="${item.seatQuantity}"/></td>
            <td><c:out value="${item.dailyPrice}"/></td>
            <td><c:out value="${item.status}"/></td>
            <td><c:out value="${item.type}"/></td>
            <td><c:out value="${item.fuelType}"/></td>
            <sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
                <td><a href="/vehicles/update/${item.id}" class="btn btn-sm btn-default">update </a>
                    <a href="/vehicles/delete/${item.id}" class="btn btn-sm btn-default"> delete</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>


</body>
</html>