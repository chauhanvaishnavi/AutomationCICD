@tag
Feature: Purchase the product from Ecommerce website
  I want to use this template for my feature file

 Background: 
	Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting an order 
    Given Logged in with username <name> and password <password>
    When I add the product  <productName> to cart
    And  checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 
    
    Examples: 
      | name                    | password    | productName |
      | helloselenium@gmail.com | Chauhan@123 | ZARA COAT 3 |
     
