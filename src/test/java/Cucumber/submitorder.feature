
@tag
Feature: I want to purchase item from ecommerce wensite
  I want to use this template for my feature file

Background: 
	Given land on ecommerce page

  @Regression
  Scenario Outline: verify submit order functionality
    Given login with username <name> and password <password>
    When I select <product> and click on submit order
    Then "THANKYOU FOR THE ORDER." messgae should be displayed

    Examples: 
      | name  							| password 					| product  				|
      | rajpatti@gmail.com 	| Revariya@2019 		| ADIDAS ORIGINAL |
      
