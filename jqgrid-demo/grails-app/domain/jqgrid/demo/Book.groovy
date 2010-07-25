package jqgrid.demo

class Book {

  String title
  String isbn
  
  static constraints = {
    title(required: true)
    isbn(required: true)
  }
}
