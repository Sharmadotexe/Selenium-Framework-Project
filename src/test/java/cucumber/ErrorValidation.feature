@tag
Feature: Error validation for an E-Commerce Website


  Background:
    Given I landed on Ecommerce Page


  @tag2
  Scenario Outline: Validating error messages
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed


    Examples:
      | name                  |   password    |
      | vikas.sh@gmail.com    |   Test@1234    |