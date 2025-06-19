Feature:Crud for API BOOK

  The purpose of these test is to cover CRUD flows for API Books

  Scenario: An authorized user is able to Add, Delete and Update a book.
    Given A list of books is available
    When I add a book to my inventory list
    Then The book is added 201
    When I delete a book from my inventory list
    Then The book is removed 204
    When I update a book from my inventory list
    Then The book is updated 200