
       jQuery("#${gridVals.id}Grid").jqGrid({
          url: '${gridVals.listUrl}',
          editurl: '${gridVals.editUrl}',
          colNames: [${gridVals.colNames}],
          colModel: [${gridVals.colModel}],
          datatype: '${gridVals.dataType}',
          autowidth: true,
          sortname: '${gridVals.sortName}',
          sortorder: '${gridVals.sortOrder}',
          scrollOffset: ${gridVals.scrollOffset},
          height: ${gridVals.height},
          rowNum: ${gridVals.rowNum},
          rowList: ${gridVals.rowList},
          viewrecords: ${gridVals.viewRecords.toBoolean()},
          gridview: ${gridVals.gridView.toBoolean()},
          cellEdit: ${gridVals.cellEdit.toBoolean()},
          caption: '${gridVals.caption}',
          hidegrid: ${gridVals.hideGrid},
          pager: jQuery('#${gridVals.id}GridPager')
     	 
     	 // Handlers
	     <g:if test="${gridVals.onDblClickRow}">
	          ,ondblClickRow: ${gridVals.onDblClickRow}
	     </g:if>
	     <g:if test="${gridVals.beforeRequest}">
	 	   ,beforeRequest: ${gridVals.beforeRequest}
  		 </g:if>
		 <g:if test="${gridVals.loadBeforeSend}">
		   ,loadBeforeSend: ${gridVals.loadBeforeSend}
		 </g:if>
		 <g:if test="${gridVals.serializeGridData}">
		   ,serializeGridData: ${gridVals.serializeGridData}
		</g:if>
      	<g:if test="${gridVals.loadError}">
   	   	   ,loadError: ${gridVals.loadError}
	    </g:if>
        <g:if test="${gridVals.gridComplete}">
           ,gridComplete: ${gridVals.gridComplete}
	    </g:if>	      
        <g:if test="${gridVals.loadComplete}">
     	   ,loadComplete: ${gridVals.loadComplete}
        </g:if>

   // End jqgrid script
   });

   // Navigation Bar
   $('#${gridVals.id}Grid').navGrid('#${gridVals.id}GridPager', {
       add: ${gridVals.standardAddButton},
       edit: ${gridVals.standardEditButton},
       del: ${gridVals.standardDeleteButton},
       search: ${gridVals.standardSearchButton},
       refresh: ${gridVals.standardRefreshButton}
   });

   // Deal with fiter toolbar if requested
   <g:if test="${gridVals.filterToolBar.toBoolean()}">
       <g:render template="/templates/filterToolBar" plugin="jqgrid" model="[gridVals:gridVals]" />
   </g:if>

   ${body()}