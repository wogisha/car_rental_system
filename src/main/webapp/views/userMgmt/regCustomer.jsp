<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer Registration</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div align="center" style="width: 50%">
        <h1>Please Complete all the fields!</h1>
        <form:form method="POST" modelAttribute="customer">
            <input name="id" type="hidden" value="${customer.id}">
            <div class="form-group">
                <label>Full Name</label>
                <form:input path="fullName" cssClass="form-control" value="${customer.fullName}"/>
                <form:errors path="fullName" cssClass="form-text text-warning"/>
            </div>

            <div class="form-group">
                <label>Liscence Number</label>
                <form:input path="licenseNumber" cssClass="form-control" value="${customer.licenseNumber}"/>
                <form:errors path="licenseNumber" cssClass="form-text text-warning"/>
            </div>

            <div class="form-group">
                <label>Nationality</label>
                <form:input path="nationality" cssClass="form-control" value="${customer.nationality}"/>
                <form:errors path="nationality" cssClass="form-text text-warning"/>
            </div>

                <div class="form-group">
                    <label>Country</label>
                    <form:input path="country" cssClass="form-control" value="${customer.country}"/>
                    <form:errors path="country" cssClass="form-text text-warning"/>
                </div>

            <div class="form-group">
                <label>City</label>
                <form:input path="city" cssClass="form-control" value="${customer.city}"/>
                <form:errors path="city" cssClass="form-text text-warning"/>
            </div>

            <div class="form-group">
                <label>Mobile Number</label>
                <form:input path="mobileNumber" cssClass="form-control" value="${customer.mobileNumber}"/>
                <form:errors path="mobileNumber" cssClass="form-text text-warning"/>
            </div>
            <button type="submit" class="btn btn-default"> Update Information</button>
            <a href="/" class="btn btn-default"> Back Home</a>
        </form:form>
    </div>
</div>

</body>
</html>