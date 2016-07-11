%{--
    @todo write code to build a template for toolbar to show on top of table with some buttons i.e. Edit, Delete
--}%
<script type="text/caair-template" id="gridToolbar">
<ul id="menuGrid" class="caairGridMenu">
    <sec:access url="/role/update">
        <li onclick="editRole();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>

    <sec:access url="/role/delete">
        <li onclick="deleteRole();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    $(document).ready(function () {
        // @todo write code here to initialize form, bind method with form for submit event, initialize button, dropdown etc.
    });

    function deleteRole() {
        // @todo write code to delete Role from table
    }

    function editRole() {
        // @todo write code to show Role object in edit mode on create panel
    }

    function listRole() {
        // @todo write code to show list of Role
    }
</script>


