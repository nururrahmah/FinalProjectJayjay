Feature: Demoblaze Website Testing

  Background:
    Given I am on the Demoblaze homepage

  Scenario: Open home page
    Then I should see the Demoblaze homepage loaded

  Scenario: Navigate to a product page
    When I click on a product "Samsung galaxy s6"
    Then I should see the product details for "Samsung galaxy s6"

  Scenario: Add product to cart
    When I click on a product "Samsung galaxy s6"
    And I add the product to the cart
    Then I should see a confirmation alert

  Scenario: Login with valid credentials
    When I log in with username "testuserfundamentalist" and password "test123"
    Then I should be logged in successfully

  Scenario: Place an order successfully
    Given I have a product in the cart
    When I place an order with name "John Doe" and credit card "123456789012"
    Then I should see a purchase confirmation

  Scenario: Login with invalid credentials
    When I log in with username "wronguser" and password "wrongpass"
    Then I should see a login error alert with message "Wrong password."
