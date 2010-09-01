package jqgrid.demo

import grails.converters.JSON;

class BookController {

    def scaffold = true

    def defaultAction = 'list'
	
	def editJSON = {
		def result
		def message = ""
		def state = "FAIL"
		def id

		// determine our action. Grid will pass a param called "oper"
		switch (params.oper) {
			// Delete Request
			case 'del':
				result = Book.get(params.id)
				if (result) {
					result.delete()
					message = "Book '${result.title}' Deleted"
					state = "OK"
				}
				break;
			// Add Request
			case 'add':
				result = new Book(params)
				break;
			// Edit Request
			case 'edit':
				// add or edit instruction sent
				result = Book.get(params.id)
				result.properties = params
				break;
		}

		// If we aren't deleting the object then we need to validate and save.
		// Capture any validation messages to display on the client side
		if (result && params.oper != "del") {
			if (!result.hasErrors() && result.save(flush: true)) {
				message = "Book  '${result.title}' " + (params.oper == 'add') ? "Added" : "Updated"
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
		def sortIndex = params.sidx ?: 'title'
		def sortOrder  = params.sord ?: 'asc'

		def maxRows = Integer.valueOf(params.rows)
		def currentPage = Integer.valueOf(params.page) ?: 1

		def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

		def books = Book.createCriteria().list(max: maxRows, offset: rowOffset) {

			if (params.title)
				ilike('title', '%' + params.title + '%')

			if (params.isbn)
				ilike('isbn', '%' + params.isbn + '%')
				
			order(sortIndex, sortOrder).ignoreCase()
		}

		def totalRows = books.totalCount
		def numberOfPages = Math.ceil(totalRows / maxRows)

		def jsonCells = books?.collect {
			[
				 cell: [
					it.title,
					it.isbn
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
