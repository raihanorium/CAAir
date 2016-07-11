<%@ page import="java.text.SimpleDateFormat" %>
<div class="container-fluid">

    <div class="row">
        <div class="col-md-12">
            <h2>Available Flight</h2>
            <hr>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="20%">Airplane Name</th>
                    <th width="15%">Departure Airport</th>
                    <th width="15%">Arrival Airport</th>
                    <th width="20%">Departure Time</th>
                    <th width="20%">Arrival Time</th>
                    <th width="10%"></th>
                </tr>
                </thead>
                <tbody>
                <g:each var="flight" in="${flightList}">
                    <tr>
                        <td width="20%">${flight.name}</td>
                        <td width="15%">${flight.departureAirPort}</td>
                        <td width="15%">${flight.arrivalAirPort}</td>
                        <td width="20%">${new SimpleDateFormat("dd MMM, yyyy [hh:mm a]").format(flight.departureTime)}</td>
                        <td width="20%">${new SimpleDateFormat("dd MMM, yyyy [hh:mm a]").format(flight.arrivalTime)}</td>
                        <td width="10%" style="text-align: center"><button class="btn btn-xs btn-danger"
                                                                           data-toggle="popover" title="Remove Flight"
                                                                           data-placement="left"
                                                                           data-content="This feature is not completed yet. But skeleton is implemented with proper documentation so that anyone can implement this feature"><i
                                    class="fa fa-remove"></i></button></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p class="well"><i>Note:</i> This is a quick implementation. A complete implementation will be done during real implementation
            </p>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>