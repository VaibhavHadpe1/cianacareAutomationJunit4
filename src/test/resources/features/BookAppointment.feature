@run

Feature: Book Appointment

  @TC1
  Scenario: Verify user navigates to Book Appointment screen on click on Book
    Given User is present on dashboard
    When User clicks on Book button
    Then User should be navigated to Book Appointment screen

  @TC2
  Scenario: Verify error messages are displayed for empty mandatory fields on Book Appointment form
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Clicks on Bill patient button without entering any data
    Then Error messages should be displayed for all mandatory fields

  @TC3
  Scenario: Book quick In clinic appointment for registered user using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters registered mobile number
    And Selects existing patient
    And Selects In clinic as type of appointment
    And Clicks on Bill later
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC4
  Scenario: Book quick online appointment for registered user using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters registered mobile number
    And Selects existing patient
    And Selects Online as type of appointment
    And Clicks on Bill patient
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC5
  Scenario: Book quick inclinic appointment for family member using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters registered mobile number
    And Selects a family member from patient list
    And Selects In clinic as type of appointment
    And Clicks on Bill later
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC6
  Scenario: Book quick online appointment for family member using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters registered mobile number
    And Selects a family member from patient list
    And Selects Online as type of appointment
    And Fills in all required details
    And Clicks on Bill patient
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC7
  Scenario: Book quick online appointment for unregistered user using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters new mobile number one
    And Enters patient name, gender, and DOB one
    And Selects Online as type of appointment
    And Clicks on Bill later
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC8
  Scenario: Book quick inclinic appointment for unregistered user using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters new mobile number two
    And Enters patient name, gender, and DOB two
    And Selects In clinic as type of appointment
    And Clicks on Bill patient
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC9
  Scenario: Schedule in clinic appointment for registered user using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters registered mobile number
    And Selects existing patient
    And Selects In clinic as type of appointment
    And Selects a time slot
    And Clicks on Bill later
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC10
  Scenario: Schedule online appointment for registered user using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters registered mobile number
    And Selects existing patient
    And Selects Online as type of appointment
    And Selects a time slot
    And Clicks on Bill patient
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC11
  Scenario: Schedule inclinic appointment for family member using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters registered mobile number
    And Selects a family member
    And Selects In clinic as type of appointment
    And Selects a time slot
    And Clicks on Bill later
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC12
  Scenario: Schedule online appointment for family member using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters registered mobile number
    And Selects a family member
    And Selects Online as type of appointment
    And Selects a time slot
    And Fills in all required details
    And Clicks on Bill patient
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC13
  Scenario: Schedule online appointment for unregistered user using Bill later option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters new mobile number three
    And Enters patient name, gender, and DOB three
    And Selects Online as type of appointment
    And Selects a time slot
    And Fills in all required details
    And Clicks on Bill later
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC14
  Scenario: Schedule in clinic appointment for unregistered user using Bill patient option
    Given User navigated and present on Book Appointment screen
    When User selects Schedule appointment
    And Enters new mobile number four
    And Enters patient name, gender, and DOB four
    And Selects In clinic as type of appointment
    And Selects a time slot
    And Fills in all required details
    And Clicks on Bill patient
    Then Appointment should be scheduled successfully
    And User should be navigated to dashboard screen

  @TC15
  Scenario: Book appointment using Add new member option from patient list
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Selects Add new member from patient dropdown
    And Enters patient name, gender, and DOB five
    And Clicks on Bill patient
    Then Appointment should be booked successfully
    And User should be navigated to dashboard screen

  @TC16
  Scenario: Add and edit services while booking appointment
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Enters all required details
    And Adds multiple services
    And Edits the services
    And Clicks on Bill later
    Then Appointment should be booked with updated services
    And User should be navigated to dashboard screen

  @TC17
  Scenario: Book appointment for different doctor if available
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Selects a doctor from the doctor dropdown
    And Enters all required patient details
    And Clicks on Bill patient
    Then Appointment should be booked with selected doctor
    And User should be navigated to dashboard screen

  @TC18
  Scenario: Book appointment by providing predefined and custom symptoms
    Given User navigated and present on Book Appointment screen
    When User selects Quick appointment
    And Selects symptoms from dropdown
    And Enters custom symptoms
    And Fills in all required patient details
    And Clicks on Bill later
    Then Appointment should be booked with symptoms
    And User should be navigated to dashboard screen
