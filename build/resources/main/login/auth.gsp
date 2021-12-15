<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <oauth2:connect provider="google" id="google-connect-link"><button class="btn btn-primary btn-sm">Sign In With Google</button></oauth2:connect>
        </br></br>
        <oauth2:connect provider="okta" id="google-connect-link"><button class="btn btn-warning btn-sm">Sign In With Okta</button></oauth2:connect>
        <br>
<hr>

        <oauth2:ifLoggedInWith provider="google">Logged with google? yes</oauth2:ifLoggedInWith>
        <oauth2:ifNotLoggedInWith provider="google"> Logged with google? no</oauth2:ifNotLoggedInWith>
        <br>
        <oauth2:ifLoggedInWith provider="okta">Logged with Okta? yes</oauth2:ifLoggedInWith>
        <oauth2:ifNotLoggedInWith provider="okta"> Logged with Okta? no</oauth2:ifNotLoggedInWith>
        <div id="controllers" role="navigation">
            <h2>Available Controllers:</h2>
            <ul>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                    </li>
                </g:each>
            </ul>
        </div>
    </section>
</div>

</body>
</html>
