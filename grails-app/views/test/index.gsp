<meta name="layout" content="main">

<g:if test="${message}">
    <g:if test="${isError}">
        <div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            ${message}
        </div>
    </g:if>
    <g:else>
        <div class="alert alert-dismissable alert-success">
            <button type="button" class="close" data-dismiss="alert">×</button>
            ${message}
        </div>
    </g:else>

</g:if>

<h3>Create Airplane</h3>

<div class="provider-list jumbotron">
    <g:form  name="airPlaneForm" controller="airPlane" action="create">

        <div class="form-group">
            <label for="name">Name :</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="totalSeat">Total Seat :</label>
            <input type="text" class="form-control" id="totalSeat" name="totalSeat">
        </div>
        <button type="submit" class="btn btn-default">Create</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </g:form>
</div>

<table></table><table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th>Name</th>
        <th>Total Seat</th>
        <th style="text-align: center"></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>asdf</td>
        <td>asdf</td>
        <td><button class="btn btn-xs btn-danger"><i class="fa fa-remove"></i></button></td>
    </tr>
    <tr>
        <td>asdf</td>
        <td>asdf</td>
        <td><button class="btn btn-xs btn-danger"><i class="fa fa-remove"></i></button></td>
    </tr>
    <tr>
        <td>asdf</td>
        <td>asdf</td>
        <td><button class="btn btn-xs btn-danger"><i class="fa fa-remove"></i></button></td>
    </tr>
    <tr>
        <td>asdf</td>
        <td>asdf</td>
        <td><button class="btn btn-xs btn-danger"><i class="fa fa-remove"></i></button></td>
    </tr>
    </tbody>
</table>