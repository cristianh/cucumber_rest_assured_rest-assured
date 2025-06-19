Feature: Book inventory management via API

  As an authorized user
  I want to be able to Add, Delete and Update a books
  So that I can manage my personal inventory through the API

  Background:
    Given I am an authorized user
    And A list of books is available

  Scenario: Add a book to the inventory
    When I send a POST request to add a new book
    Then The response status code should be 201
    And The book should be appear in the inventory list

  Scenario: Delete a book from the inventory
    When I send a DELETE request for an existing book
    Then The response status code should be 204
    And The book should no longer appear in the inventory list

  Scenario: Update a book in the inventory
    When I send a PUT request with updated details for an existing book
    Then The response status code should be 200
    And The inventory should reflect the updated book information