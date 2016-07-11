<meta name="layout" content="main">

<div class="container-fluid">

    <div class="row">
        <div class="col-md-12">
            <h2>Airplane</h2>
            <hr>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div id="application_top_panel" class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        Create Airplane
                    </div>
                </div>

                <form id="airPlaneForm" name="airPlaneForm" class="form-horizontal" role="form">
                    <div class="panel-body">
                        <div class="form-group col-md-7">
                            <label for="name">Name :</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>

                        <div class="col-md-1">
                        </div>

                        <div class="form-group col-md-4">
                            <label for="totalSeat">Total Seat :</label>
                            <input type="text" class="form-control" id="totalSeat" name="totalSeat">
                        </div>

                    </div>

                    <div class="panel-footer">
                        <button id="create" name="create" type="button" data-role="button"
                                class="btn btn-primary"
                                role="button"
                                aria-disabled="false" data-toggle="popover" title="Create AirPlane"
                                data-content="This feature is not completed yet. But skeleton is implemented with proper documentation so that anyone can implement this feature"
                                data-placement="top">
                            Create
                        </button>

                        <button id="clearFormButton" name="clearFormButton" type="button" data-role="button"
                                class="btn btn-default" role="button"
                                aria-disabled="false">
                            Cancel
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th width="50%">Name</th>
                    <th width="30%">Total Seat</th>
                    <th width="20%" style="text-align: center">Remove</th>
                </tr>
                </thead>
                <tbody>

                <g:each in="${list}" var="airPlane">
                    <tr>
                        <td>${airPlane.name}</td>
                        <td>${airPlane.totalSeat}</td>
                        <td style="text-align: center">
                            <button class="btn btn-xs btn-danger"
                                    data-toggle="popover" title="Remove Airplane"
                                    data-content="This feature is not completed yet. But skeleton is implemented with proper documentation so that anyone can implement this feature"
                                    data-placement="top">
                                <i class="fa fa-remove"></i>
                            </button>
                        </td>
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