@run
Feature: Services

  As a user
  I want to add,edit,delete the services.

  @TC1
  Scenario: Verify user navigates to Services screen on clicking Services from Dashboard
    Given User is present on dashboard
    When User clicks on Services from the dashboard
    Then User should be navigated to the Services screen

  @TC2
  Scenario: Verify user navigates to Add Services on clicking Add icon
    Given User is on the Services screen
    When User clicks on the Add Service button
    Then User should be navigated to the Add Service form

  @TC3
  Scenario: Verify user is not able to add empty services
    Given User is on the Add Service form
    When User clicks on the Save button without entering any details
    Then An error message should be displayed for missing mandatory fields

  @TC4
  Scenario: Verify user is able to add a service by providing valid data
    Given User is on the Services screen
    When User clicks on the Add Service button
    And User enters valid service details
    And User clicks on the Save button
    Then The new service should be added successfully and displayed in the services list

  @TC5
  Scenario: Verify user is able to edit an existing service
    Given User is present on dashboard
    When User clicks on Services from the dashboard
    And User selects a service from the list and clicks on the Edit icon
    And User updates the service details
    And User clicks on the Save button
    Then The service details should be updated successfully

  @TC6
  Scenario: Verify user is able to delete a service
    Given User is present on dashboard
    When User clicks on Services from the dashboard
    And User selects a service from the list and clicks on the Delete icon
    Then The service should be removed from the list successfully

  @TC7
  Scenario: Verify user is able to add a service by providing valid data
    Given User is on the Services screen
    When User clicks on the Add Service button
    And User enters valid service details
    And User clicks on the Save button
    Then The new service should be added successfully and displayed in the services list
    Then user navigates to dashboard