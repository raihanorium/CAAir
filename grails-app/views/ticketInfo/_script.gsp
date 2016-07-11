%{--
    @todo write code to build a template for toolbar to show on top of table with some buttons i.e. Edit, Delete
--}%
<script type="text/caair-template" id="gridToolbar">
<ul id="menuGrid" class="caairGridMenu">
    <sec:access url="/ticketInfo/update">
        <li onclick="editTicketInfo();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>

    <sec:access url="/ticketInfo/delete">
        <li onclick="deleteTicketInfo();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    $(document).ready(function () {
        // @todo write code here to initialize form, bind method with form for submit event, initialize button, dropdown etc.
    });

    function deleteTicketInfo() {
        // @todo write code to delete Ticket Info from table
    }

    function editTicketInfo() {
        // @todo write code to show Ticket Info object in edit mode on create panel
    }

    function listTicketInfo() {
        // @todo write code to show list of Ticket Info
    }
</script>


