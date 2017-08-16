<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<body class="container">

<h3>Reservations</h3>

<form action="/reservations" method="get" class="pull-right">

    <input type="text" name="q" placeholder="Customer Licence" value="<c:out value="${param.q}"/>">
    <button class="btn btn-default"> Search</button>
</form>

<ul class="pagination center-block">
    <c:forEach var="i" begin="1" end="${reservations.totalPages}">
        <li class="${page == i ? 'active':' '}"><a href="/reservations?q=${param.q}&page=<c:out value = "${i}"/>" ><c:out value = "${i}"/> </a> </li>
    </c:forEach>
</ul>

 <a href="/" class="btn btn-default">Back Home</a>


<p>${reservation_message}</p>

<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th> Car</th>
        <th> When</th>
        <th> For</th>
        <th> Status</th>
    </tr>

    <c:forEach items="${reservations.iterator()}" var="item">
    <tr>
        <td><a href="/reservations/<c:out value="${item.id}"/>"><c:out value="${item.id}"/></a></td>
        <td><c:out value="${item.vehicle} / #${item.vehicle.id}"/></td>
        <td><c:out value="${    item.pickupDate}"/></td>
        <td><c:out value="${item.customer.fullName} - ${item.customer.licenseNumber}"/></td>
        <td><c:out value="${item.status}"/></td>
    <tr>
        </c:forEach>
</table>


</body>
</html>