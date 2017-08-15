<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Information</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div align="center" style="width: 50%">
            <div class="form-group">
                <div align="center">
                    <table class="table">
                        <caption><h2>List of users</h2></caption>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>UserName</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var="emp" items="${empList}">
                            <tr>
                                <td><c:out value="${emp.id}" /></td>
                                <td><c:out value="${emp.fullName}" /></td>
                                <td><c:out value="${emp.address}" /></td>
                                <td><c:out value="${emp.username}" /></td>
                                <td><c:out value="${emp.role}" /></td>
                                <td><a href="<c:url value="/emp/update/${emp.id}"/>">Update</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        <a href="<c:url value="/emp/new"/>" class="btn btn-default"> + Add</a>
        <a href="<c:url value="/"/>" class="btn btn-default"> Back Home</a>

    </div>
</div>

</body>
</html>