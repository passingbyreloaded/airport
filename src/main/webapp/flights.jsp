<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Type</th>
        <th>Number</th>
        <th>Date Time</th>
        <th>Air Company</th>
        <th>Aircraft</th>
        <th>Pilot</th>
        <th>Gate Number</th>
        <th>Parking Spot</th>
        <th>Current Status</th>
        <th>Change Status</th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${flights}" var="flight">
        <jsp:useBean id="flight" scope="page" type="ru.innopolis.stc13.to.FlightWithRestrictions"/>
        <c:choose>
            <c:when test="${flight.responsibleRole.name().equals(sessionScope.role)}">
                <form action="/flights?action=update" method="post">
                    <input type="hidden" name="id" value="${flight.id}">
                    <tr>
                        <td>${flight.flightType}
                        </td>
                        <td>${flight.number}
                        </td>
                        <td>${fn:formatDateTime(flight.dateTime)}
                        </td>
                        <td>${flight.airCompany.name}
                        </td>
                        <td>${flight.aircraft}
                        </td>
                        <td>${flight.pilot.name}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${flight.gateEditable}">
                                    <input type="text" name="gateNumber" value="${flight.gateNumber}">
                                </c:when>
                                <c:otherwise>
                                    ${flight.gateNumber}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${flight.parkingSpotEditable}">
                                    <input type="text" name="parkingSpot" value="${flight.parkingSpot}">
                                </c:when>
                                <c:otherwise>
                                    ${flight.parkingSpot}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${flight.status.toString()}
                        </td>
                        <td>
                            <select name="status" value="${flight.status}">
                                <c:forEach items="${flight.availableStatuses}" var="status">
                                    <option <c:if test="${status==flight.status}"> selected</c:if>
                                            value="${status}">
                                            ${status.toString()}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="submit">
                        </td>

                    </tr>

                </form>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>${flight.flightType}
                    </td>
                    <td>${flight.number}
                    </td>
                    <td>${fn:formatDateTime(flight.dateTime)}
                    </td>
                    <td>${flight.airCompany.name}
                    </td>
                    <td>${flight.aircraft}
                    </td>
                    <td>${flight.pilot.name}
                    </td>
                    <td>${flight.gateNumber}
                    </td>
                    <td>${flight.parkingSpot}
                    </td>
                    <td>${flight.status.toString()}
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>
<br>
<%
    if ("PILOT".equals(request.getSession().getAttribute("role"))) {%>
<a href="flights?action=add">Add flight</a>
<%}%>
</body>
</html>
