package jqgrid.demo

import grails.converters.JSON

class AuthorController {

    def scaffold = true
    
    def defaultAction = "list"

    def editJSON = {

    }

    def listJSON = {
        def sortIndex = params.sidx ?: 'name'
        def sortOrder  = params.sord ?: 'asc'

        def maxRows = Integer.valueOf(params.rows)
        def currentPage = Integer.valueOf(params.page) ?: 1

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

        def authors = Author.createCriteria().list(max: maxRows, offset: rowOffset) {

            if (params.firstName)
                ilike('firstName', '%' + params.firstName + '%')

            if (params.lastName)
                ilike('lastName', '%' + params.lastName + '%')

            if (params.isbn)
                ilike('isbn', '%' + params.isbn + '%')

            order(sortIndex, sortOrder).ignoreCase()
        }

        def totalRows = authors.totalCount
        def numberOfPages = Math.ceil(totalRows / maxRows)

        def jsonCells = authors?.collect {
            [
             	cell: [ 
                    it.firstName,
                    it.lastName
                ],
             	id: it.id
            ]
        }

        def jsonData= [rows: jsonCells,
                       page: currentPage,
                       records: totalRows,
                       total: numberOfPages]

        render jsonData as JSON
    }
}
