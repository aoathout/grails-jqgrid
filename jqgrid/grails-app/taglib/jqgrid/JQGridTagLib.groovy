package jqgrid

class JQGridTagLib {

    static namespace = "jqgrid"
    
    /**
     * Include CSS and JavaScript resources in head.
     */
    def resources = { attrs, body ->
        out << render(template:"${pluginContextPath}/grails-app/views/templates/resources")
    }

    /**
     * Generates the required javascript and html for jqGrid
     * -- attrs.id : The id of the wrapper, grid and pager
     * -- attrs.createHolder : Create the wrapper, table and pager html elements.
     * -- attrs.resizeOffset : The offset to use for grid resize
     * -- attrs.listUrl : The url that will provide the list data
     * -- attrs.editUrl : The url that will process an edit/add/delete
     * -- attrs.dataType : The list content datatype
     * -- attrs.autoWidth : The jqgrid autowidth flag
     * -- attrs.sortName : The default column to sort by
     * -- attrs.sortOrder : The default sort order (asc, desc)
     * -- attrs.scrollOffset : The offset value for the scrollbar in jqgrid
     * -- attrs.height : The height of the grid
     * -- attrs.rowNum : The numbers of rows to show
     * -- attrs.rowList : The values to show in the rowNum change select
     * -- attrs.viewRecords :
     * -- attrs.gridView :
     * -- attrs.colNames : The names of the columns. Pass in a comma separated string
     *                     but don't add [ or ], These will be added when script is
     *                     written out.
     * -- attrs.colModel : The row definitions. There are many options. See
     *                     jqGrid documentation
     * -- attrs.filterToolBar : true to show the filter bar, default is false
     * -- attrs.filterToolBarSearchOnEnter : true to perform search when enter is pressed
     * -- attrs.viewRecords : true to show the beginning and ending record numbers
     * -- attrs.gridView : true to use speed improvement, there are some limitations
     *                     Check the jqgrid documentation for details
     */
    def grid = { attrs, body ->
        // Set default values
        def gridVals = [:]
        gridVals.createHolder    = attrs.remove('createHolder') ?: true
        gridVals.caption         = attrs.remove('caption') ?: ''
        gridVals.hideGrid        = attrs.remove('hideGrid') ?: true
        gridVals.resizeOffset    = attrs.remove('resizeOffset') ?: -2
        gridVals.dataType        = attrs.remove('dataType') ?: 'json'
        gridVals.sortName        = attrs.remove('sortName') ?: 'id'
        gridVals.sortOrder       = attrs.remove('sortOrder') ?: 'asc'
        gridVals.scrollOffset    = attrs.remove('scrollOffset') ?: 0
        gridVals.height          = attrs.remove('height') ?: 300
        gridVals.rowNum          = attrs.remove('rowNum') ?: 25
        gridVals.rowList         = attrs.remove('rowList') ?: [25, 50, 75, 100]
        gridVals.viewRecords     = attrs.remove('viewRecords') ?: true
        gridVals.gridView        = attrs.remove('gridView') ?: true
        gridVals.listUrl         = attrs.remove('listUrl') ?: 'No Url Specified'
        gridVals.editUrl         = attrs.remove('editUrl') ?: 'No Url Specified'
        gridVals.colNames        = attrs.remove('colNames') ?: "'id'"
        gridVals.colModel        = attrs.remove('colModel') ?: '{name:"id"}'
        gridVals.filterToolBar   = attrs.remove('filterToolBar') ?: false
        gridVals.searchOnEnter   = attrs.remove('searchOnEnter') ?: true
        gridVals.cellEdit        = attrs.remove('cellEdit') ?: false

        // Standard grid nav bar buttons
        gridVals.add             = attrs.remove('add') ?: false
        gridVals.edit            = attrs.remove('edit') ?: false
        gridVals.delete          = attrs.remove('delete') ?: false
        gridVals.search          = attrs.remove('search') ?: false
        gridVals.refresh         = attrs.remove('refresh') ?: true

        // Write out the table
        if (gridVals.createHolder.toBoolean()) {
            out << render(template:"${pluginContextPath}/grails-app/views/templates/gridWrapper", model:[gridVals:gridVals])
        }

        // Write out the grid javascript
        out << render(template:"${pluginContextPath}/grails-app/views/templates/grid", model:[gridVals:gridVals])
    }

    def addButton = {

    }

    def editButton = {

    }

    def searchButton = {

    }

    def deleteButton = {

    }
}
