%{-- documentation --}%
<script type="text/caair-template" id="gridToolbar">
<ul id="menuGrid" class="caairGridMenu">
    <sec:access url="/airPlane/update">
        <li onclick="editAirPlane();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>

    <sec:access url="/airPlane/delete">
        <li onclick="deleteProject();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });


    function listAirPlane() {
        // @todo
    }
</script>


