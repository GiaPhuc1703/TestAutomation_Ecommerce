
Feature: Login functionality

Scenario: Open landing page.
    Given I am on the landing page.
    Then User clicks on login button.
    Then User enters email and password.
    Then User click Login button.
    Then User validates if login is success.
    Then User performs validation on the home page.
    Then User clicks on View Product.
    Then User performs validation on product description page.
    Then User clicks on add to cart.
    Then User validates the pop up.
    When User clicks on view cart.
    Then User performs validation on the cart page.
    When User clicks on Proceed to checkout.
    Then User performs validation on checkout page.
    When User clicks on Place order button.
    Then User enters the payment details.
    Then User clicks on Pay and Confirm button.
    Then User performs validation on the Congratulations page.
