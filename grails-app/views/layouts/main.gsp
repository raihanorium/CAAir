<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title><g:layoutTitle default="CAAir"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

    <r:require modules="style, fontAwesome"/>
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>
<div class="container-fluid" style="height: 100%;">

<div class="row">
<div class="col-md-1"></div>
<div class="col-md-10">
<g:render template="/layouts/navbar"/>
</div>
<div class="col-md-1"></div>
</div>

<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-10" id="mainContent">
        <g:render template="/templates/flashMessages"/>
        <g:layoutBody/>
    </div>

    <div class="col-md-1"></div>
</div>

<div class="row">
    <div class="col-md-12">
        &nbsp;
    </div>
</div>

<g:render template="/layouts/footer"/>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<g:javascript library="application"/>

<r:layoutResources/>
</div>
</body>
</html>