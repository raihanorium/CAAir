<%@ page import="java.text.SimpleDateFormat" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th width="20%">Air Plane</th>
        <th width="10%">Departure</th>
        <th width="10%">Arrival</th>
        <th width="30%">Departure Time</th>
        <th width="30%">Arrival Time</th>
    </tr>
    </thead>
    <tbody>
    <g:each var="flight" in="${list}">
        <tr>
            <td width="20%">${flight.name}</td>
            <td width="10%">${flight.departureAirPort}</td>
            <td width="10%">${flight.arrivalAirPort}</td>
            <td width="30%">${new SimpleDateFormat("dd MMM, yyyy [hh:mm a]").format(flight.departureTime)}</td>
            <td width="30%">${new SimpleDateFormat("dd MMM, yyyy [hh:mm a]").format(flight.arrivalTime)}</td>
        </tr>
    </g:each>
    </tbody>
</table>