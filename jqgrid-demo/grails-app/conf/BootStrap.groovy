import jqgrid.demo.Author
import jqgrid.demo.Book

class BootStrap {

    def init = { servletContext ->
        def henryRollins = new Author(firstName: 'Henry', lastName: 'Rollins').save()
        def nikkiSix = new Author(firstName: 'Nikki', lastName: 'Sixx').save()
    }

    def destroy = {
    }
}
