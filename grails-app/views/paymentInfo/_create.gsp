<div class="container-fluid">
    <div class="row">
        <div id="application_top_panel" class="panel panel-primary">
            <div class="panel-heading">
                <span class="pull-left"></span>

                <div class="panel-title">
                    Create Payment Info
                </div>
            </div>

            <form id="paymentInfoForm" name="paymentInfoForm" class="form-horizontal form-widgets" role="form">
                <div class="panel-body">
                    %{--
                        @todo write html code to build create form i.e.
                        <input type="text" id="payment No" class="form-control" placeholder="Payment No" required="">
                    --}%
                </div>

                <div class="panel-footer">
                    <button id="create" name="create" type="submit" data-role="button"
                            class="btn btn-primary"
                            role="button"
                            aria-disabled="false">
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

    <div class="row">
        %{--
            @todo write html code to show list of Payment Info in table i.e.

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th># SL.</th>
                        <th>Payment No</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1.</td>
                        <td>Payment No 01</td>
                    </tr>
                </tbody>
            </table>
        --}%
    </div>
</div>