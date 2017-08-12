<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>


<title>Vehicles</title>
</head>
<body>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Add Vehicle</h1>

			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="newVehicle" class="form-horizontal">
			<fieldset>
				<legend>Add New Vehicle</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="vehicletype">
					<spring:message code="addVehicle.form.vehicletype.label" /></label>
					<select class="col-sm-2 col-lg-2" name="menu" id="menu">
						<c:forEach var="menu" items="${types}">
							<option value="${menu.id}">${menu.description}
								<c:if test="${menu.id == newVehicle.vehicleType}">selected
           						</c:if>
							</option>
						</c:forEach>
					</select>
				</div>

				<!--  div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="vehicleId"><spring:message code="addVehicle.form.vehicleId.label"/></label>
					<div class="col-lg-10">
						<form:input id="vehicleId" path="vehicleId" type="text" class="form:input-large"/>
						<form:errors path="vehicleId" cssClass="text-danger"/>
					</div>
				</div-->

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2 control-label" for="makeyear">
					<spring:message code="addVehicle.form.makeyear.label" /></label>
					<div class="col-lg-10">
						<form:input id="makeyear" path="makeyear" type="text"
							class="form:input-small" />
						<form:errors path="makeyear" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="make"><spring:message
							code="addVehicle.form.make.label" /></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="make" path="make" type="text"
								class="form:input-small" />
							<form:errors path="make" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="model"><spring:message
							code="addVehicle.form.model.label" /></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="model" path="model" type="text"
								class="form:input-small" />
							<form:errors path="model" cssClass="text-danger" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="plateNumber"><spring:message
							code="addVehicle.form.plateNumber.label" /></label>
					<div class="col-lg-10">
						<form:input id="plateNumber" path="plateNumber" />
						<form:errors path="plateNumber" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="numberOfSeats"><spring:message
							code="addVehicle.form.numberOfSeats.label" /></label>
					<div class="col-lg-10">
						<form:input id="numberOfSeats" path="numberOfSeats" />
						<form:errors path="numberOfSeats" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="dailyRate"><spring:message
							code="addVehicle.form.dailyRate.label" /></label>
					<div class="col-lg-10">
						<form:input id="dailyRate" path="dailyRate" />
						<form:errors path="dailyRate" cssClass="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="dailyFine"><spring:message
							code="addVehicle.form.dailyFine.label" /></label>
					<div class="col-lg-10">
						<form:input id="dailyFine" path="dailyFine" />
						<form:errors path="dailyFine" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="isAvailable"><spring:message
							code="addVehicle.form.isAvailable.label" /></label>
					<div class="col-lg-10">
						<form:checkbox id="isAvailable" path="isAvailable" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Add" />
					</div>
				</div>

			</fieldset>
		</form:form>
	</section>
</body>
</html>
