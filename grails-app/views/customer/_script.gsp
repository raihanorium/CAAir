%{--
    @todo write code to build a template for toolbar to show on top of table with some buttons i.e. Edit, Delete
--}%
<script type="text/caair-template" id="gridToolbar">
<ul id="menuGrid" class="caairGridMenu">
    <sec:access url="/customer/update">
        <li onclick="editCustomer();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>

    <sec:access url="/customer/delete">
        <li onclick="deleteCustomer();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    $(document).ready(function () {
        // @todo write code here to initialize form, bind method with form for submit event, initialize button, dropdown etc.
    });

    function deleteCustomer() {
        // @todo write code to delete Customer from table
    }

    function editCustomer() {
        // @todo write code to show Customer object in edit mode on create panel
    }

    function listCustomer() {
        // @todo write code to show list of Customer
    }
</script>


