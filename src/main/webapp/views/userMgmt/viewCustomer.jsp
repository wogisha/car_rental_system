<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer Information</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div align="center" style="width: 50%">
        <div class="form-group">
            <div align="center">
                <table class="table">
                    <caption><h2>List of Customers</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Nationality</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>Licence Number</th>
                        <th>Mobile Number </th>

                    </tr>
                    <c:forEach var="cust" items="${custList}">
                        <tr>
                            <td><c:out value="${cust.id}" /></td>
                            <td><c:out value="${cust.fullName}" /></td>
                            <td><c:out value="${cust.nationality}" /></td>
                            <td><c:out value="${cust.country}" /></td>
                            <td><c:out value="${cust.city}" /></td>
                            <td><c:out value="${cust.licenseNumber}" /></td>
                            <td><c:out value="${cust.mobileNumber}" /></td>

                            <%--<td><a href="<c:url value="/cust/updateCust/${cust.id}"/>">Update</a></td>--%>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <a href="<c:url value="/"/>" class="btn btn-default"> Back home </a>

    </div>
</div>

</body>
</html>