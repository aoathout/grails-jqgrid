
<%@ page import="jqgrid.demo.Author" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'author.label', default: 'Author')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <jq:resources />
        <jqui:resources />
        <jqgrid:resources />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body" style="width:95%;">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>

            <jqgrid:grid
                id="author"
                listUrl="${createLink(action: 'listJSON')}"
                editUrl="${createLink(action: 'editJSON')}"
                dataType="JSON"
                caption="Authors"
                colNames="'Firstname', 'Lastname', 'id'"
                colModel="{name:'firstName', editable: true},
                          {name:'lastName', editable: true},
                          {name:'id', hidden: true}"
                sortName="lastName"
                sortOrder="asc"
                scrollOffset="2"
                height="150"
                rowNum="5"
                rowList="[5, 10, 15, 20, 25]"
                gridView="true"
                viewRecords="true"
                resizeOffset="-2"
                filterToolBar="true"
                searchOnEnter="true"/>
        </div>
    </body>
</html>
