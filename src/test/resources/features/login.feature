Feature: Login on nopCommerce

  In order to test the login functionality
  As a registered user
  I want to verify both successful and unsuccessful login attempts

  @loginSimple
  Scenario: Successful login with valid credentials
    Given the user is on home page
    When the user Navigates to the login page
    And the user enters a valid username and password
    Then the message  "Login successful" should be displayed

    @loginWithParams
  Scenario: Successful login with valid credentials with params
    Given the user is on home page
    When the user Navigates to the login page
    And the user enters username "admin@gmail.com" and password "adminb$KTrV2"
    Then the message  "Login successful" should be displayed

    @loginWithExamples
  Scenario Outline: Successful login with valid credentials with examples
    Given the user is on home page
    When the user Navigates to the login page
    And the user enters "<username>" and "<password>"
    Then the message  "Login successful" should be displayed
    Examples:
    | username                 | password     |
    | admin@gmail.com          | admin!b$KTrV2 |
    | admin                    | Admin321     |