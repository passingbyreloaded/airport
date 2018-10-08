<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Flight</title>
</head>
<body>
<form action="/flights" method="post">
    <h2>Flight Type:</h2>
    <%--<input type="text" name="flightType">--%>
    <select name="flightType">
        <c:forEach items="${types}" var="type">
            <option value="${type}">
                    ${type}
            </option>
        </c:forEach>
    </select>
    <h2>Number:</h2>
    <input type="text" name="number">
    <h2>Date and Time:</h2>
    <input type="datetime-local" name="dateTime">
    <h2>Air Company:</h2>
    <input type="text" name="airCompany" value="<%=request.getSession().getAttribute("company")%>">
    <h2>Aircraft:</h2>
    <input type="text" name="aircraft">
    <h2>Pilot:</h2>
    <input type="text" name="pilot" value="<%=request.getSession().getAttribute("login")%>">
    <h2>Gate Number:</h2>
    <input type="text" name="gateNumber">
    <h2>Parking Spot:</h2>
    <input type="text" name="parkingSpot">
    <h2>Status:</h2>
    <%--<input type="text" name="status">--%>
    <select name="status">
        <c:forEach items="${statuses}" var="status">
            <option value="${status}">
                    ${status}
            </option>
        </c:forEach>
    </select>
    <br>
    <br>
    <br>
    <input type="submit">
</form>
</body>
</html>
