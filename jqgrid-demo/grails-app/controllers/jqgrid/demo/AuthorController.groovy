package jqgrid.demo

import grails.converters.JSON

class AuthorController {

    def messageSource
    
    def scaffold = true
    
    def defaultAction = "list"

    def editJSON = {
        def result
        def message = ""
        def state = "FAIL"
        def id

        // determine our action. Grid will pass a param called "oper"
        switch (params.oper) {
            // Delete Request
            case 'del':
                result = Author.get(params.id)
                if (result) {
                    result.delete()
                    message = "Author '${result.firstName} ${result.lastName}' Deleted"
                    state = "OK"
                }
                break;
            // Add Request
            case 'add':
                result = new Author(params)
                break;
            // Edit Request
            case 'edit':
                // add or edit instruction sent
                result = Author.get(params.id)
                result.properties = params
                break;
        }

        // If we aren't deleting the object then we need to validate and save.
        // Capture any validation messages to display on the client side
        if (result && params.oper != "del") {
            if (!result.hasErrors() && result.save(flush: true)) {
                message = "Author  '${result.firstName} ${result.lastName}' " + (params.oper == 'add') ? "Added" : "Updated"
                id = result.id
                state = "OK"
            } else {
                message = "<ul>"
                result.errors.allErrors.each {
                    message += "<li>${messageSource.getMessage(it)}</li>"
                }
                message += "</ul>"
            }
        }

        //render [message:message, state:state, id:id] as JSON
        def jsonData = [messsage: message, state: state, id: id]
        render jsonData as JSON
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
