<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<a href="/rent" class="btn btn-default">Rent out car</a>
<a href="/reservations/<c:out value="${id}/cancel"/>" class="btn btn-default" >Cancel Reservation</a>
</body>
</html>