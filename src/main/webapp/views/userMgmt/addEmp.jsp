<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Employee Registration</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div align="center" style="width: 50%">
        <h1>Please Complete all the fields!</h1>
        <form:form method="POST" commandName="" modelAttribute="employee">
            <div class="form-group">
                <label>Name</label>
                <form:input path="fullName" cssClass="form-control" value="${employee.fullName}"/>
                <form:errors path="fullName" cssClass="form-text text-warning"/>
            </div>

            <div class="form-group">
                <label>Address</label>
                <form:input path="address" cssClass="form-control" value="${employee.address}"/>
                <form:errors path="address" cssClass="form-text text-warning"/>
            </div>

            <div class="form-group">
                <label>User Name</label>
                <form:input path="username" cssClass="form-control" value="${employee.username}"/>
                <form:errors path="username" cssClass="form-text text-warning"/>
            </div>

            <c:if test="${not update}">
                <div class="form-group">
                    <label>Password</label>
                    <form:password path="password" cssClass="form-control"/>
                    <form:errors path="password" cssClass="form-text text-warning"/>
                </div>
            </c:if>

            <div class="form-group">
                <label>Role</label>
                <form:select path="role" items="${roleList}" cssClass="form-control"/>
                <form:errors path="role" cssClass="form-text text-warning"/>
            </div>

            <button type="submit" class="btn btn-default"> Save</button>
            <button type="reset" class="btn btn-default"> Reset</button>

            <c:if test="${not empty employee.id}">
                <a href="<c:url value="/deactivate/${employee.id}"/>" class="btn btn-default"> Deactive</a>
            </c:if>
            <a href="/emp/viewEmp " class="btn btn-default">Back to Users</a>
        </form:form>
    </div>
</div>

</body>
</html>