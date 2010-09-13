package jqgrid

class JQGridTagLib {

    static namespace = "jqgrid"

    /**
     * Include CSS.
     */
    def cssResources = { attrs, body ->
        out << render(template:"${pluginContextPath}/grails-app/views/templates/cssResources")
    }

    /**
     * Include JavaScript.
     */
    def scriptResources = { attrs, body ->
        out << render(template:"${pluginContextPath}/grails-app/views/templates/scriptResources")
    }

    /**
     * Include CSS and JavaScript resources in head.
     */
    def resources = { attrs, body ->
        out << render(template:"${pluginContextPath}/grails-app/views/templates/resources")
    }

    def wrapper = { attrs, body ->
		def gridVals = attrs
		gridVals.showPager = attrs.remove('showPager') ?: true
        out << render(template:"${pluginContextPath}/grails-app/views/templates/gridWrapper", model:[gridVals:gridVals, body:body])
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
     * -- attrs.showPger : true to show pager, false to hide 
     */
    def grid = { attrs, body ->
        // Set default values
        def gridVals = attrs
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
		gridVals.showPager		 = attrs.remove('showPager') ?: true
		
        // Standard grid nav bar buttons
        gridVals.standardAddButton     = attrs.remove('standardAddButton') ?: false
        gridVals.standardEditButton    = attrs.remove('standardEditButton') ?: false
        gridVals.standardDeleteButton  = attrs.remove('standardDeleteButton') ?: false
        gridVals.standardSearchButton  = attrs.remove('standardSearchButton') ?: false
        gridVals.standardRefreshButton = attrs.remove('standardRefreshButton') ?: true

        // Write out the grid javascript
        out << render(template:"${pluginContextPath}/grails-app/views/templates/grid", model:[gridVals:gridVals, body:body])
    }

    def addButton = {attrs, body ->
        def gridVals = attrs
        gridVals.url = attrs.remove('url') ?: 'No Url Specified'
        out << render(template:"${pluginContextPath}/grails-app/views/templates/addButton", model:[gridVals:gridVals])
    }

    def editButton = {attrs, body ->
        def gridVals = attrs
        gridVals.url = attrs.remove('url') ?: 'No Url Specified'
        gridVals.messageId = attrs.remove('messageId') ?: 'message'
        out << render(template:"${pluginContextPath}/grails-app/views/templates/editButton", model:[gridVals:gridVals])
    }

    def searchButton = {attrs, body ->
        out << render(template:"${pluginContextPath}/grails-app/views/templates/searchButton", model:[gridVals:attrs])
    }

    def deleteButton = {attrs, body ->
        def gridVals = attrs
        gridVals.url = attrs.remove('url') ?: 'No Url Specified'
        gridVals.messageId = attrs.remove('messageId') ?: 'message'
        out << render(template:"${pluginContextPath}/grails-app/views/templates/deleteButton", model:[gridVals:gridVals])
    }
}
