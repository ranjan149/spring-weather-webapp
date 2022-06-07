<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding-top: 10px;
        padding-bottom: 20px;
        padding-left: 30px;
        padding-right: 40px;
    }
</style>
<title>Weather Forecast</title>
</head>
<body>
	<div class="container">
			<h2>Weather Information</h2>
			<hr />
			<div class="panel panel-info">
				<div class="panel-body">
				<table>
				    <thead>
                		<tr>
                		<th>City Id</th>
                		<th>City Name</th>
                		<th>Weather Description</th>
                		<th>Temperature</th>
                		</tr>
                    </thead>
                    <tbody>
                        <c:forEach var="response" items="${responses}">
                            <tr>
                               <td>${response.id}</td>
                               <td>${response.name}</td>
                               <td>${response.description}</td>
                               <td>${response.temperature}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                </div>
            </div>
    </div>
</body>
</html>
