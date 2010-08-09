<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css',contextPath:"")}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico',contextPath:"")}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" contextPath="" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif',contextPath:"")}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png',contextPath:"")}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>