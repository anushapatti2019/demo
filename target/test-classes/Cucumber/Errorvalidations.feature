
@tag
Feature: I want to login ecommerce website
  I want to use this template for my feature file

  @Errorvalidation
  Scenario Outline: Verify login functionality
    Given land on ecommerce page
    When login with username <name> and password <password>
    Then "Incorrect email or password." should be displayed

    Examples: 
      | name  							| password 					| 
      | rajpatti@gmail.com 	| Revariyaa@2019 		| 
