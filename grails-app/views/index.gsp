<%@ page defaultCodec="none" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <style type="text/css">
    #mainContent a {
        text-decoration: none;
    }
    </style>
</head>

<body>
<sec:ifNotLoggedIn>
    <gopd:ifAnyDisconnected>
        <form action="/CAAir/login/checkLogin" method="post">
        <div class="row">
            <div class="col-md-6">
                <h3>Air Line Staff</h3>

                <div class="row">
                    <div class="col-md-3">
                        <label for="inputPassword">User ID :</label>
                    </div>

                    <div class="col-md-6">
                        <input type="email" name="j_username" id="inputEmail" class="form-control" placeholder="Email address" required=""
                               autofocus="" value="airstaff@caair.com">
                    </div>
                </div>
                <div class="row">&nbsp;</div>
                <div class="row">
                    <div class="col-md-3">
                        <label for="inputPassword">Password :</label>
                    </div>

                    <div class="col-md-6">
                        <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Password"
                               required="" value="airstaff">
                    </div>
                </div>

                <div class="row">&nbsp;</div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <button class="btn btn-primary btn-block" type="submit">Login</button>
                    </div>
                </div>

                <div class="row">&nbsp;</div>
                <div>
                    <g:if test="${message}">
                        <div class='alert alert-danger col-md-12' id='login_msg_'
                             style='margin-bottom: 0'>${message}</div>
                    </g:if>
                </div>
            </div>

            <div class="col-md-6">
                <h3>Customer</h3>

                <div class="provider-list jumbotron">
                    <oauth:disconnected provider="google">
                        <oauth:connect provider="google" title="Google">
                            <i class="fa fa-google-plus-square fa-4x" style="color: #2aabd2"></i>
                        </oauth:connect>
                    </oauth:disconnected>

                    <i class="fa fa-linkedin-square fa-4x" onclick="" style="color: #ccc"></i>

                    <i class="fa fa-twitter-square fa-4x" style="color: #ccc"></i>

                </div>
            </div>
        </div>
        </form>

        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row">&nbsp;</div>
        <div class="row"><i>Note :</i></div>
        <div class="row">
            <i>
                <b>Customer : </b>Please login with Google+ because Google+ integration is only implemented in this quick assignment, skeleton of LinkedIn and Twitter is added in this project for Junior programmer to ensure success integration.
            </i>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <i>
                <b>Air Line Staff : </b>Please Click on "Login" button to login as Airline Staff. For simplicity we have provided ID and password in login form.
                In this project authentication is done using spring security, documentation is added to guide programmers to integrate with active directory using LDAP protocol.
            </i>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <i>
                <b>Administrator : </b> To login as Admin, use following credentials 'Login Id: admin@caair.com, Password: admin'.
            </i>
        </div>

    </gopd:ifAnyDisconnected>
</sec:ifNotLoggedIn>

</body>
</html>
