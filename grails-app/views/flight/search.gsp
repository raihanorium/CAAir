<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Search Flight</title>

</head>

<body>

<div class="row">
    <div class="col-md-4">
        <div class="row">
            <div class="col-md-3">
                <label>From :</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="from" class="form-control" required="" value="USA">
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                <label>To:</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="to" class="form-control" required="" value="Dhaka">
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                <label>Depart:</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="depart" class="form-control" required="" value="2016-06-30">
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                <label>Arrival:</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="arrival" class="form-control" required="" value="2016-07-01">
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                <label>Adult:</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="adult" class="form-control" required="" value="2">
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                <label>Child:</label>
            </div>

            <div class="col-md-8">
                <input type="text" id="children" class="form-control" required="" value="1">
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <div class="row">
            <div class="col-md-3">
                &nbsp;
            </div>

            <div class="col-md-8">
          <button id="search" name="search"
                        class="btn btn-primary btn-block"
                        type="button" onclick="searchFlight()"
                        role="button"
                        data-role="button"
                        aria-disabled="false"
                        data-placement="bottom">
                    Search
                </button>
            </div>
        </div>
    </div>

    <div class="col-md-8" id="flightListDiv">
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
    </div>
</div>

<div class="row">&nbsp;</div>

<div class="row">
    <div class="col-md-12">
        <p class="well"><i>Note:</i> Please click "Search" button above to filter result. Departure and arrival times will be date control, which is not added for time limitation.
        </p>
    </div>
</div>

<script language="javascript">
    function searchFlight() {
        var from = $("#from").val();
        var to = $("#to").val();
        var depart = $("#depart").val();
        var arrival = $("#arrival").val();
        var searchUrl = "/CAAir/flight/showSearchResult?from=" + from + "&to=" + to + "&depart=" + depart + "&arrival=" + arrival;
        $.ajax({
            url: searchUrl,
            success: executePostConditionForSearch,
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            },
            complete: function (XMLHttpRequest, textStatus) {

            },
            dataType: 'json',
            type: 'post'
        });
    }

    function executePostConditionForSearch(data) {
        $("#flightListDiv").html(data.html);
    }

</script>

</body>
</html>
