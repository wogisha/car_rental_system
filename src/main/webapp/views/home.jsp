<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car Rental System</title>
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body class="container">
<h3 align="center">Car rental System</h3>

<a href="/vehicles/displayAllVehicles " class="btn btn-block btn-default">Vehicles </a>

<a href="/reservations " class="btn btn-block btn-default">Reservations </a>


<sec:authorize access="hasAnyAuthority('ROLE_MANAGER','ROLE_EMPLOYEE')">
    <a href="/emp/viewEmp " class="btn btn-block btn-default">Users </a>

    <a href="/rental " class="btn btn-block btn-default">Rent</a>
    <a href="/cust/viewCust" class="btn btn-block btn-default">Customers</a>
</sec:authorize>
<sec:authorize access="hasAnyAuthority('ROLE_CUSTOMER')">
<a href="/cust/profile " class="btn btn-block btn-default">Profile </a>
</sec:authorize>
<a href="/emp/changePw " class="btn btn-block btn-default">Change password </a>
<br>
<form action="/logout" method="post">
    <input type="submit" value="Logout" class="btn btn-block btn-default"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</body>
</html>