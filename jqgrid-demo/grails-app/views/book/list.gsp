<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <jqgrid:cssResources />
        
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

          $(document).ready(function () {
	        <jqgrid:grid
	            id="book"
	            listUrl="${createLink(action: 'listJSON')}"
	            editUrl="${createLink(action: 'editJSON')}"
	            colNames="'Title', 'ISBN', 'id'"
	            colModel="{name:'title', editable: true},
	                      {name:'isbn', editable: true},
	                      {name:'id', hidden: true}"
	            sortName="title"
	            caption="${entityName} List"
	            height="300"
	            viewRecords="true"
	            filterToolBar="true"
	            searchOnEnter="false"            
	            onDblClickRow="onDblClickRow">
	
	            <jqgrid:deleteButton
	                id="book"
	                url="${createLink(action: 'editJSON')}" />
	
	            <jqgrid:editButton
	                id="book"
	                url="${createLink(action: 'edit')}" />
	
	            <jqgrid:addButton
	                id="book"
	                url="${createLink(action: 'create')}" />
	
	            <jqgrid:searchButton id="book" />

	        </jqgrid:grid>
          });
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

            <jqgrid:wrapper id="book" />
        </div>
    </body>
</html>
