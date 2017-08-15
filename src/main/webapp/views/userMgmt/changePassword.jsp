<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Owner Registration</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div align="center" style="width: 50%">
        <h1>Please Complete all the fields!</h1>
        <form method="post" action="<c:url value="/emp/changePw"/>">

            <div class="form-group">
                <label>Old Password</label>
                <input name="olPpassword" class="form-control" required="required" type="password"/>
            </div>
            <div class="form-group">
                <label>New Password</label>
                <input name="newPassword1" class="form-control" required="required" type="password"/>
            </div>

            <div class="form-group">
                <label>Confirm Password</label>
                <input name="newPassword2" class="form-control" required="required" type="password" />
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <button type="submit" class="btn btn-default" > Change </button>
            <button type="button" class="btn btn-default" > Cancel </button>
            <a href="/" class="btn btn-default"> back home</a>

        </form>
    </div>
</div>

</body>
</html>