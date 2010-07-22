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

        // Send the javascript
        href = createLinkTo(dir: "${pluginContextPath}/js/jqgrid", file: 'jquery.jqGrid.min.js')
        out << """<script type="text/javascript" src="${href}"></script>"""
    }

    def grid = { attrs ->

    }
}
