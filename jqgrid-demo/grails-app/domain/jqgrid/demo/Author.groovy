package jqgrid.demo

class Author {

  String firstName
  String lastName

  static hasMany = [books: Book]

  static constraints = {
      firstName(blank: false)
      lastName(blank: false)
      books(nullable: true)
  }
}
