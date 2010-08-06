
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

        <script type="text/javascript">
          function onDblClickRow(id) {
              if (id) {
                  var url = "${createLink(action: 'show')}";
                  $(location).attr('href', url + "/" + id);
              }
          }
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <br />
        <div class="body" style="width:95%;">
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>

            <jqgrid:grid
                id="author"
                createHolder="true"
                listUrl="${createLink(action: 'listJSON')}"
                editUrl="${createLink(action: 'editJSON')}"
                colNames="'Firstname', 'Lastname', 'id'"
                colModel="{name:'firstName', editable: true},
                          {name:'lastName', editable: true},
                          {name:'id', hidden: true}"
                sortName="lastName"
                caption="Author List"
                height="300"
                filterToolBar="true"
                onDblClickRow="onDblClickRow"
                add="true"
                edit="true"
                search="true"
                delete="true"/>
        </div>
    </body>
</html>
