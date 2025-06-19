Feature: Register on nopcommerce Application
    Scenario: Successful account registration on nopCommerce
    
    Given the visitor is on the registration page
    "https://demo.nopcommerce.com/register"
    When the visitor fills out the registration form with his
    information
    And the visitor clicks the Register button
    Then the visitor should see a confirmation message
    indicating that the account was created successfully