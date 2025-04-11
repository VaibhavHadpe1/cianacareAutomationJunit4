@run
Feature: Reports
  As an Admin,
  I want to view and filter reports
  So that I can analyze them effectively

  @TC1
  Scenario: Verify user navigates to Reports screen on clicking the Reports feature
    Given The user is present on dashboard
    When The user clicks on the Reports feature
    Then The user should be navigated to the Reports screen

  @TC2
  Scenario: Verify default message displayed when the doctor has no reports
    Given The user is on the Reports screen
    When The user has no reports available
    Then A default message No reports available should be displayed

  @TC3
  Scenario: Verify user can filter consultation revenue reports by date
    Given The user is on the Reports screen
    When The user selects a date range for consultation revenue reports
    Then The filtered consultation revenue reports should be displayed date wise

  @TC4
  Scenario: Verify user can filter consultation revenue reports by Appointment type
    Given The user is on the Reports screen
    When The user selects an Appointment type filter
    Then The filtered consultation revenue reports should be displayed according to appointment type

  @TC5
  Scenario: Verify user can filter consultation revenue reports by Payment mode
    Given The user is on the Reports screen
    When The user selects a Payment mode filter
    Then The filtered consultation revenue reports should be displayed according to payment mode

  @TC6
  Scenario: Verify user can filter consultation revenue reports by Doctors
    Given The user is on the Reports screen
    When The user selects a doctor from the filter options
    Then The filtered consultation revenue reports should be displayed for the selected doctor

  @TC7
  Scenario: Verify user can export consultation revenue to Excel format
    Given The user is on the Reports screen
    When The user clicks on Export to Excel for consultation revenue
    Then The consultation revenue report should be displayed in Excel format

  @TC8
  Scenario: Verify user can export consultation revenue to PDF format
    Given The user is on the Reports screen
    When The user clicks on Export to PDF for consultation revenue
    Then The consultation revenue report should be displayed in PDF format

  @TC9
  Scenario: Verify user can view service revenue on selecting the service revenue tab
    Given The user is on the Reports screen
    When The user clicks on the Service Revenue tab
    Then The service revenue report should be displayed

  @TC10
  Scenario: Verify user can filter service revenue by date
    Given The user is on the Reports screen
    When The user selects a date range for service revenue reports
    Then The filtered service revenue reports should be displayed by date

  @TC11
  Scenario: Verify user can export service revenue to PDF format
    Given The user is on the Reports screen
    When The user clicks on Export to PDF for service revenue
    Then The service revenue report should be displayed in PDF format

  @TC12
  Scenario: Verify user can view patients on selecting the Patients tab
    Given The user is on the Reports screen
    When The user clicks on the Patients tab
    Then The patient details should be displayed

  @TC13
  Scenario: Verify user can filter patients by date
    Given The user is on the Reports screen
    When The user selects a date range for patient records
    Then The filtered patient records should be displayed according to dates

  @TC14
  Scenario: Verify user can filter patients by gender
    Given The user is on the Reports screen
    When The user selects a Gender filter
    Then The filtered patient records should be displayed according to gender

  @TC15
  Scenario: Verify user can filter patients by age
    Given The user is on the Reports screen
    When The user selects an Age filter
    Then The filtered patient records should be displayed accordingly

  @TC16
  Scenario: Verify user can export patient details to Excel format
    Given The user is on the Reports screen
    When The user clicks on Export to Excel for patient details
    Then The patient details report should be displayed in Excel format

  @TC17
  Scenario: Verify user can export patient details to PDF format
    Given The user is on the Reports screen
    When The user clicks on Export to PDF for patient details
    Then The patient details report should be displayed in PDF format
    Then User logout from dashboard
