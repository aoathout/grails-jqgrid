package jqgrid

class JQGridTagLib {

    static namespace = "jqgrid"
    
    /**
     * Include CSS and JavaScript resources in head.
     */
    def resources = { attrs ->
        // Send the css
        def href = createLinkTo(dir: "${pluginContextPath}/css/jqgrid", file: 'ui.jqgrid.css')
        out << """<link rel="stylesheet" href="${href}" type="text/css" media="screen" />\n"""
        
        // Send the jqgrid lables, etc
        // TODO: Grab the correct locale file, don't just default
        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid/i18n", file: 'grid.locale-en.js')
        out << """<script type="text/javascript" src="${href}"></script>\n"""

        // Send the jqgrid code
        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid", file: 'jquery.jqGrid.min.js')
        out << """<script type="text/javascript" src="${href}"></script>\n"""

        // Send the jqgrid fluid code
        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid", file: 'jquery.jqGrid.fluid.js')
        out << """<script type="text/javascript" src="${href}"></script>\n"""
    }

    /**
     * Generates the required javascript and html for jqGrid
     * -- attrs.id : The id of the wrapper, grid and pager
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
    def grid = { attrs ->
        def id              = attrs.remove('id') ?: 'grid'
        def caption         = attrs.remove('caption') ?: ''
        def hideGrid        = attrs.remove('hideGrid') ?: true
        def resizeOffset    = attrs.remove('resizeOffset') ?: -2
        def dataType        = attrs.remove('dataType') ?: 'json'
        def sortName        = attrs.remove('sortName') ?: 'id'
        def sortOrder       = attrs.remove('sortOrder') ?: 'asc'
        def scrollOffset    = attrs.remove('scrollOffset') ?: 0
        def height          = attrs.remove('height') ?: 300
        def rowNum          = attrs.remove('rowNum') ?: 25
        def rowList         = attrs.remove('rowList') ?: [25, 50, 75, 100]
        def viewRecords     = attrs.remove('viewRecords') ?: true
        def gridView        = attrs.remove('gridView') ?: true

        def listUrl         = attrs.remove('listUrl') ?: 'No Url Specified'
        def editUrl         = attrs.remove('editUrl') ?: 'No Url Specified'

        def colNames        = attrs.remove('colNames') ?: "'id'"
        def colModel        = attrs.remove('colModel') ?: '{name:"id"}'

        def filterToolBar   = attrs.remove('filterToolBar') ?: false
        def searchOnEnter   = attrs.remove('filterToolBarSearchOnEnter') ?: true

        def cellEdit        = attrs.remove('cellEdit') ?: false


        // Write out the table
        out << """<div id="${id}Wrapper" class="ui-widget-header ui-corner-all">\n
                        <table id="${id}Grid" class="scroll jqTable"></table>\n
                        <div id="${id}GridPager" class="scroll"></div>\n
                  </div>\n"""

        // Write opening script tag
        out << """<script type="text/javascript">\n"""

        // Write resize function
        out << """function resize_${id}_grid() {
                        \$('#${id}Grid').fluidGrid({
                            base:'#${id}Wrapper',
                            offset: ${resizeOffset}
                  })};\n"""

        // Write the jqgrid script
        out << """\$(document).ready(function () {
                      jQuery("#${id}Grid").jqGrid({
                         url: '${listUrl}',
                         editurl: '${editUrl}',
                         colNames: [${colNames}],
                         colModel: [${colModel}],
                         datatype: '${dataType}',
                         autowidth: true,
                         sortname: '${sortName}',
                         sortorder: '${sortOrder}',
                         scrollOffset: ${scrollOffset},
                         height: ${height},
                         rowNum: ${rowNum},
                         rowList: ${rowList},
                         viewrecords: ${viewRecords},
                         gridview: ${gridView},
                         cellEdit: ${cellEdit},
                         caption: '${caption}',
                         hidegrid: ${hideGrid},
                         pager: jQuery('#${id}GridPager')
                      });\n"""

        // Deal with fiter toolbar if requested
        if (filterToolBar) {
            out << """\$('#${id}Grid').filterToolbar({
                            autosearch: true,
                            searchOnEnter: ${searchOnEnter}
                        });"""
        }

        // End document.ready
        out << """});\n"""

        // Resize the grid when the browser is resized
        out << """\$(window).resize(resize_${id}_grid);"""

        // Write closing script tag
        out << """</script>\n"""
    }
}
