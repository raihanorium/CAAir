%{--
    write code to build a template for toolbar to show on top of table with some buttons i.e. Edit, Delete
--}%
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
        // @todo
    });

    function deleteProject() {
//
    }

    function editAirPlane() {
//        documentation
    }

    function listAirPlane() {
        // @todo
    }
</script>


