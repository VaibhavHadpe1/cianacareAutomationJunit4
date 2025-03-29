@run
Feature: Template Management
  As a doctor
  I want to manage templates for treatments
  So that I can reuse them efficiently

  @TC1
  Scenario: Verify user navigates to Template screen on click on Templates feature
    Given The user is logged in as doctor and present on dashboard
    When The user clicks on the Templates feature
    Then The user should be navigated to the Template screen

  @TC2
  Scenario: Verify default message displayed when doctor doesn't have templates
    Given The user is on the Template screen
    When The doctor has no templates
    Then A default message No templates available should be displayed

  @TC3
  Scenario: Verify user navigates to Create Template screen on clicking add icon
    Given The user is on the Template screen
    When The user clicks on the Add icon
    Then The user should be navigated to the Create Template screen

  @TC4
  Scenario: Verify user not able to create an empty template and error messages are displayed
    Given The user is on the Create Template screen
    When The user clicks on the Save button without filling required fields
    Then Appropriate error messages should be displayed for mandatory fields

  @TC5
  Scenario: Verify user able to create a template by adding only medication with valid details
    Given The user is on the Template screen
    When The user clicks on the Add icon
    And The user enters valid template details and only medication details
    And Clicks on the Save button
    Then The template should be created successfully

  @TC6
  Scenario: Verify user able to create a template by adding only investigation with valid details
    Given The user is on the Template screen
    When The user clicks on the Add icon
    And The user enters valid template and only investigation details
    And Clicks on the Save button
    Then The template with only investigation should be created successfully

  @TC7
  Scenario: Verify user able to create a template by adding medication and investigation with valid details
    Given The user is on the Template screen
    When The user clicks on the Add icon
    And The user enters valid medication details and investigation details
    And Clicks on the Save button
    Then The template with medicine and investigation should be created successfully

  @TC8
  Scenario: Verify user able to create a template by any Gender and Age group combination
    Given The user is on the Template screen
    When The user clicks on the Add icon
    And The user selects any combination of Gender and Age group
    And Enters required details
    And Clicks on the Save button
    Then The template should be created successfully for different combination

  @TC9
  Scenario: Verify user able to create a template for any Gender and any Age group
    Given The user is on the Template screen
    When The user clicks on the Add icon
    And The user selects Any for Gender and Any for age group
    And Enters required details
    And Clicks on the Save button
    Then The template should be created successfully for any combination

  @TC10
  Scenario: Verify user able to edit the existing templates
    Given The user is on the Template screen
    When User selects an existing template to edit
    And Updates the details
    And Clicks on the Save button
    Then The template should be updated successfully

  @TC11
  Scenario: Verify user able to delete the existing templates
    Given The user is on the Template screen
    When User selects an existing template and click on delete
    Then The template should be removed successfully

  @TC12
  Scenario: Verify user able to search the template using search functionality
    Given The user is on the Template screen
    When The user enters a template name in the search bar
    Then The relevant templates should be displayed in the results
