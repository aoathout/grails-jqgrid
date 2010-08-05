<script type="text/javascript">
        // Write the jqgrid script
        $(document).ready(function () {
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
               hidegrid: ${gridVals.hideGrid.toBoolean()},
               pager: jQuery('#${gridVals.id}GridPager')

          // Handlers
          <g:if test="${gridVals.onDblClickRow}">
               ondblClickRow: ${gridVals.onDblClickRow}
          </g:if>

        // End jqgrid script
        });

        // Navigation Bar
        $('#${gridVals.id}Grid').navGrid('#${gridVals.id}GridPager', {
            add: true,
            edit: true,
            del: true,
            search: true,
            refresh: true
        });

        // Deal with fiter toolbar if requested
        <g:if test="${gridVals.filterToolBar.toBoolean()}">
            <g:render template="/templates/filterToolBar" plugin="jqgrid" model="[gridVals:gridVals]" />
        </g:if>

        ${body()}

        //
        <g:render template="/templates/resize" plugin="jqgrid" model="[gridVals:gridVals]" />
        // End document.ready
        });
</script>