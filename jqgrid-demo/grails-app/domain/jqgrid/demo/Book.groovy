package jqgrid.demo

class Book {

  String title
  String isbn
  
  static belongsTo = Author
  
  static constraints = {
    title(required: true)
    isbn(required: true)
  }
  
  String toString() {
	  title
  }
  
}
