#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Search

  @tag1 @UI
  Scenario: Google Search
    Given Google Search Page is displayed
    When I enter "Facebook" in search text box
    And I click on Goole Search Button in Type Ahead Section

   @tag2 @API
  Scenario: API Test
    Given Call Get Single User API
    Then Verify Response
  