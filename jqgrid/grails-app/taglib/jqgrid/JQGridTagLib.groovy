package jqgrid

class JQGridTagLib {

    static namespace = "jqgrid"
    
    /**
     * Include CSS and JavaScript resources in head.
     */
    def resources = { attrs, body ->
//        // Send the css
//        def href = createLinkTo(dir: "${pluginContextPath}/css/jqgrid", file: 'ui.jqgrid.css')
//        out << """<link rel="stylesheet" href="${href}" type="text/css" media="screen" />\n"""
//
//        // Send the jqgrid lables, etc
//        // TODO: Grab the correct locale file, don't just default
//        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid/i18n", file: 'grid.locale-en.js')
//        out << """<script type="text/javascript" src="${href}"></script>\n"""
//
//        // Send the jqgrid code
//        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid", file: 'jquery.jqGrid.min.js')
//        out << """<script type="text/javascript" src="${href}"></script>\n"""
//
//        // Send the jqgrid fluid code
//        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid", file: 'jquery.jqGrid.fluid.js')
//        out << """<script type="text/javascript" src="${href}"></script>\n"""
          out << render(template:"${pluginContextPath}/grails-app/views/templates/resources", model:[attrs:attrs])
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
        attrs.createHolder    = attrs.createHolder ?: true
        attrs.caption         = attrs.caption ?: ''
        attrs.hideGrid        = attrs.hideGrid ?: true
        attrs.resizeOffset    = attrs.resizeOffset ?: -2
        attrs.dataType        = attrs.dataType ?: 'json'
        attrs.sortName        = attrs.sortName ?: 'id'
        attrs.sortOrder       = attrs.sortOrder ?: 'asc'
        attrs.scrollOffset    = attrs.scrollOffset ?: 0
        attrs.height          = attrs.height ?: 300
        attrs.rowNum          = attrs.rowNum ?: 25
        attrs.rowList         = attrs.rowList ?: [25, 50, 75, 100]
        attrs.viewRecords     = attrs.viewRecords ?: true
        attrs.gridView        = attrs.gridView ?: true

        attrs.listUrl         = attrs.listUrl ?: 'No Url Specified'
        attrs.editUrl         = attrs.editUrl ?: 'No Url Specified'

        attrs.colNames        = attrs.colNames ?: "'id'"
        attrs.colModel        = attrs.colModel ?: '{name:"id"}'

        attrs.filterToolBar   = attrs.filterToolBar ?: false
        attrs.searchOnEnter   = attrs.searchOnEnter ?: true

        attrs.cellEdit        = attrs.cellEdit ?: false

        // Listeners
//        attrs.onDblClickRow   = attrs.onDblClickRow

        // Write out the table
        if (attrs.createHolder.toBoolean()) {
            out << render(template:"${pluginContextPath}/grails-app/views/templates/gridWrapper", model:[attrs:attrs])
        }

        // Write opening script tag
        out << """<script type="text/javascript">\n"""

        // Write the jqgrid script
        out << """\$(document).ready(function () {
                      jQuery("#${attrs.id}Grid").jqGrid({
                         url: '${attrs.listUrl}',
                         editurl: '${attrs.editUrl}',
                         colNames: [${attrs.colNames}],
                         colModel: [${attrs.colModel}],
                         datatype: '${attrs.dataType}',
                         autowidth: true,
                         sortname: '${attrs.sortName}',
                         sortorder: '${attrs.sortOrder}',
                         scrollOffset: ${attrs.scrollOffset},
                         height: ${attrs.height},
                         rowNum: ${attrs.rowNum},
                         rowList: ${attrs.rowList},
                         viewrecords: ${attrs.viewRecords.toBoolean()},
                         gridview: ${attrs.gridView.toBoolean()},
                         cellEdit: ${attrs.cellEdit.toBoolean()},
                         caption: '${attrs.caption}',
                         hidegrid: ${attrs.hideGrid.toBoolean()},
                         pager: jQuery('#${attrs.id}GridPager')"""

        // Handlers
        if (attrs.onDblClickRow) {
            out << """,\nondblClickRow: ${attrs.onDblClickRow}"""
        }
        
        // End jqgrid script
        out << """});\n"""

        // Navigation Bar
        out << """\$('#${attrs.id}Grid').navGrid('#${attrs.id}GridPager', {
                        add: true,
                        edit: true,
                        del: true,
                        search: true,
                        refresh: true
                    });"""


        // Deal with fiter toolbar if requested
        // TODO: Use g:if tag in a template for this since we don't want a
        //       separate template just to close document.ready
        if (attrs.filterToolBar.toBoolean()) {
            out << render(template:"${pluginContextPath}/grails-app/views/templates/filterToolBar", model:[attrs:attrs])
        }

        // End document.ready
        out << """});\n"""

        // Resize the grid when the browser is resized
        out << render(template:"${pluginContextPath}/grails-app/views/templates/resize", model:[attrs:attrs])

        // Write closing script tag
        out << """</script>\n"""
    }
}
