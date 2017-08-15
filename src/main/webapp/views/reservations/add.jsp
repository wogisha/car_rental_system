<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<body class="container">

<h3>Make Car Reservation</h3>

<form:form method="post" modelAttribute="reservation" action="/reservations/add?vehicleId=${vehicle.id}">

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <input type="hidden"
           name="vehicleId"
           value="${vehicle.id}"/>
    <div>
        Vehicle <input type="text"  value="<c:out value="${vehicle}"/>" disabled="disabled">
    </div>

    <div>
        Customer
        <select name="customerId" >
            <c:forEach items="${customers}" var="customer">
                <option value="${customer.id}">${customer.fullName} - ${customer.licenseNumber}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        When you want to pick it up ? <form:input path="pickupDate" type="date" />
        <form:errors path="pickupDate" />
    </div>

    <div>
        when will you return it? <form:input path="returnDate" type="date"/>
        <form:errors path="returnDate" />
    </div>

    <button class="btn btn-default"> make reservation </button>
    <a href="/reservations" class="btn btn-default">Back to Reservations</a>

</form:form>


</body>
</html>