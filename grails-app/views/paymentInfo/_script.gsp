%{--
    @todo write code to build a template for toolbar to show on top of table with some buttons i.e. Edit, Delete
--}%
<script type="text/caair-template" id="gridToolbar">
<ul id="menuGrid" class="caairGridMenu">
    <sec:access url="/paymentInfo/update">
        <li onclick="editPaymentInfo();"><i class="fa fa-edit"></i>Edit</li>
    </sec:access>

    <sec:access url="/paymentInfo/delete">
        <li onclick="deletePaymentInfo();"><i class="fa fa-trash-o"></i>Delete</li>
    </sec:access>
</ul>
</script>

<script language="javascript">
    $(document).ready(function () {
        // @todo write code here to initialize form, bind method with form for submit event, initialize button, dropdown etc.
    });

    function deletePaymentInfo() {
        // @todo write code to delete Payment Info from table
    }

    function editPaymentInfo() {
        // @todo write code to show PaymentInfo object in edit mode on create panel
    }

    function listPaymentInfo() {
        // @todo write code to show list of PaymentInfo
    }
</script>


