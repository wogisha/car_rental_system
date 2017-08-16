<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<body class="container">

<h3>Your Reservation</h3>

<table class="table-cell table">
    <tr>
        <td>#</td>
        <td>${reservation.id}</td>
    </tr>
    <tr>
        <td>Vehicle</td>
        <td>${reservation.vehicle}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${reservation.status}</td>
    </tr>
    <tr>
        <td>Expected Pickup Date</td>
        <td>${reservation.pickupDate}</td>
    </tr>

    <tr>
        <td>Expected Return Date</td>
        <td>${reservation.returnDate}</td>
    </tr>
</table>

<a href="/reservations" class="btn btn-default">Back to Reservations</a>

<c:if test="${reservation.newReservation}">
    <sec:authorize access="hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')">
        <a href="/rental/confirmation?reservationId=${reservation.id}" class="btn btn-default">Rent out car</a>
    </sec:authorize>
    <a href="/reservations/<c:out value="${id}/cancel"/>" class="btn btn-default">Cancel Reservation</a>
</c:if>


</body>
</html>