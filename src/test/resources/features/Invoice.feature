
@run
Feature: Invoice Management
  As a user
  I want to create service invoice, lab invoice and filter it.

  @TC1
  Scenario: Verify user navigates to Invoice screen from dashboard
    Given User is present on dashboard
    When User clicks on Invoice from dashboard
    Then User should be navigated to Invoice screen

  @TC2
  Scenario: Verify user navigates to Add Service Invoice screen by selecting services
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Service
    Then User should be navigated to Add Service Invoice form

  @TC3
  Scenario: Verify user is not able to create an empty service invoice
    Given User is on Add Service Invoice form
    When User clicks on submit button without entering any details
    Then An error message should be displayed for empty mandatory fields

  @TC4
  Scenario: Verify user is able to create a service invoice for existing user
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Service
    And User enters valid  service invoice details of existing user
    And User clicks on Submit button
    Then The service invoice should be created successfully and displayed in the invoice list

  @TC5
  Scenario: Verify user is able to create a service invoice for new user
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Service
    And User enters valid  service invoice details of new user
    And User clicks on Submit button
    Then The service invoice for new user should be created successfully and displayed in the invoice list

  @TC6
  Scenario: Verify user navigates to Add Lab Invoice screen by selecting lab
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Lab
    Then User should be navigated to Add Lab Invoice form

  @TC7
  Scenario: Verify user is not able to create an empty lab invoice
    Given User is on Add Lab Invoice form
    When User clicks on submit button without entering any details
    Then An error message should be displayed for invalid mandatory fields

  @TC8
  Scenario: Verify user is able to create a lab invoice for existing user
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Lab
    And User enters valid lab invoice details of existing user
    And User clicks on Submit button
    Then The lab invoice for existing user should be created successfully and displayed in the invoice list

  @TC9
  Scenario: Verify user is able to create a lab invoice for new user
    Given User is on Invoice screen
    When User clicks on Add Invoice and selects Lab
    And User enters valid lab invoice details of new user
    And User clicks on Submit button
    Then The lab invoice for new user should be created successfully and displayed in the invoice list

  @TC10
  Scenario: Verify user is able to upload the lab reports for all types of tests
    Given User is on Invoice screen
    When User selects a lab invoice and clicks on Upload Report
    And User uploads a valid lab report file
    Then The report should be uploaded successfully and associated with the invoice

  @TC11
  Scenario: Verify user is able to view the reports
    Given User is on Invoice screen
    When User selects a lab invoice clicks on View Report
    Then The uploaded report should be displayed correctly

  @TC12
  Scenario: Verify user is able to download the lab invoice
    Given User is on Invoice screen
    When User selects a lab invoice clicks on Download button
    Then The invoice should be downloaded successfully

  @TC13
  Scenario: Verify user is able to filter invoices date wise
    Given User is on Invoice screen
    When User applies a date range filter
    Then The invoice list should display only invoices within the selected date range

  @TC14
  Scenario: Verify user is able to filter invoices by Status
    Given User is on Invoice screen
    When User selects a status filter
    Then The invoice list should display only invoices matching the selected status

  @TC15
  Scenario: Verify user is able to filter invoices by Mode of Payment
    Given User is on Invoice screen
    When User selects a payment mode filter
    Then The invoice list should display only invoices matching the selected payment mode

  @TC16
  Scenario: Verify user is able to filter invoices by Type
    Given User is on Invoice screen
    When User selects a filter for invoice type
    Then The invoice list should display only invoices matching the selected type
