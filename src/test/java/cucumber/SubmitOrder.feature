@tag
  Feature: Purchase order from an E-Commerce Website


    Background:
      Given I landed on Ecommerce Page


    @tag2
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add the <productName> from Cart
      And Checkout <productName> and submit the order
      Then "Thankyou for the order." message is displayed on ConfirmationPage


      Examples:
      | name                  |   password    | productName |
      | vikas.sh@gmail.com    |   Test@123    | ZARA COAT 3 |
