<meta name="layout" content="main">
<script>

    $(function () {
        $('#departure-datetimepicker1').datetimepicker();
        $('#arrival-datetimepicker1').datetimepicker();
    });

    function showhideArrival(val) {
        if (val == 1) {
            $("#arrival-time-div").hide();
        } else {
            $("#arrival-time-div").show();
        }
    }

    function loadSearchResult() {
        jQuery.ajax({
            type: 'post',
            data: jQuery("#search_form").serialize(),
            url: "${request.contextPath}/flightBooking/searchResult",
            success: function (html, textStatus) {
                $("#search-result-container-div").html(html);
            },
            dataType: 'html'
        });

    }
</script>

<h3>Search Flight</h3>

<div class="provider-list jumbotron">
    <g:form name="search_form" class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-6">
                <label for="departureAirport">From</label>
                <input id="departureAirport" class="form-control input-group-lg reg_name" type="text"
                       name="departureAirport"
                       title="Enter first name" placeholder="From Airport">
            </div>

            <div class="col-sm-6">
                <label for="departureTime">Departure</label>


                <div class='input-group date' id='departure-datetimepicker1' data-date-format="dd-mm-yyyy">
                    <input id="departureTime" class="form-control input-group-lg reg_name" type="text"
                           name="departureTime"
                           title="Departure Time" placeholder="dd-mm-yyyy tt:mm">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

            </div>
        </div><!--/form-group-->
        <div class="form-group">
            <div class="col-sm-6">
                <label for="arrivalAirport">To</label>
                <input id="arrivalAirport" class="form-control input-group-lg reg_name" type="text"
                       name="arrivalAirport"
                       title="Arrival Airport" placeholder="Arrival Airport">
            </div>

            <div class="col-sm-6" id="arrival-time-div">
                <label for="arrivalTime">Arrvial</label>

                <div class='input-group date' id='arrival-datetimepicker1' data-date-format="dd-mm-yyyy">
                    <input id="arrivalTime" class="form-control input-group-lg reg_name" type="text" name="arrivalTime"
                           title="Arrival Time" placeholder="dd-mm-yyyy tt:mm">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

            </div>
        </div><!--/form-group-->


        <div class="form-group">
            <div class="col-sm-6">
                <label for="adult">Adult(s)</label>
                <select class="form-control" id="adult" name="adult">
                    <option>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                </select>

            </div>

            <div class="col-sm-6">
                <label for="children">Child(ren)</label>
                <select class="form-control" id="children" name="children">
                    <option>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>

            </div>
        </div><!--/form-group-->
        <div class="form-group">
            <div class="col-sm-6">
                <label class="radio-inline"><input type="radio" id="oneway" value="1" name="optradio"
                                                   onclick="showhideArrival(this.value);">One Way</label>
                <label class="radio-inline"><input type="radio" id="roundtrip" value="2" name="optradio"
                                                   onclick="showhideArrival(this.value);">Round Trip</label>

            </div><!--/form-group-->
        </div>

        <div class="col-sm-12">
            <button type="button" class="btn btn-default" style="width: 150px;"
                    onclick="loadSearchResult();">Submit</button>
        </div>

    </g:form>

</div>

<div id="search-result-container-div"></div>