<sec:ifLoggedIn>
    <div class="navbar navbar-default navbar-fixed-top" id="navBarDivId">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <g:link url="${g.createLink(uri: '/')}" class="navbar-brand">Air Ticket Reservation System</g:link>
            </div>

            <div class="navbar-collapse collapse navbar-responsive-collapse pull-right">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-expanded="false">
                            <img src="${session.appUser.imagePath}" alt="Google"
                                 style="width: 30px;height: 30px;"/>&nbsp;${session.appUser.username}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="javascript:void(0);" onclick="showProfile()"><i class="glyphicon glyphicon-user">&nbsp;My Profile</i>
                                </a>
                            </li>
                            <li>
                                <a href="/CAAir/logout/index/google"><i
                                        class="glyphicon glyphicon-log-out">&nbsp;Logout</i></a>
                            </li>
                        </ul>
                    </li>

                    <oauth:connected provider="linkedin">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">
                                <i class="fa fa-linkedin-square fa-2x"></i> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><g:link controller="linkedin" action="me">My Details</g:link></li>
                            </ul>
                        </li>
                    </oauth:connected>

                    <oauth:connected provider="twitter">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">
                                <i class="fa fa-twitter-square fa-2x"></i> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><g:link controller="twitter" action="me">My Details</g:link></li>
                            </ul>
                        </li>
                    </oauth:connected>
                </ul>
            </div>
        </div>
    </div>
</sec:ifLoggedIn>

<script>
    function showProfile() {
        jQuery.ajax({
            type: 'post',
            url: "/CAAir/appUser/showProfile",
            success: function (data, textStatus) {
                $("#mainContent").html(data);
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }
</script>