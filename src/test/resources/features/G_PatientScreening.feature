#@run
  @Ignore
Feature: Patient Screening Flow during Appointment

  @TC1
  Scenario: Initiate appointment and navigate to patient screening
    Given The user is present on dashboard
    When The user clicks on Appointments
    And Select the appointments
    And The user initiates an appointment
    Then The user should be navigated to the Patient Screening page

  @TC2
  Scenario: Verify components of patient screening are visible
    Given The user is on the Patient Screening page
    Then All components of patient screening should be visible

  @TC3
  Scenario: Add predefined HOPI
    Given The user is on the Patient Screening page
    When The user click on HOPI
    And Searches and selects a predefined HOPI
    Then The selected HOPI should be added to the list

  @TC4
  Scenario: Remove added HOPI
    Given The user is on the HOPI screen
    When The user removes a HOPI using the cross icon
    Then The HOPI should be removed from the list
    When The user clicks the Clear All button
    Then All HOPIs should be removed

  @TC5
  Scenario: Add custom HOPI and HOPI from quick type
    Given The user is on the HOPI screen
    When The user types a custom HOPI
    Then The custom HOPI should be added
    When The user adds HOPI using quick type
    Then The selected HOPI should be added

  @TC6
  Scenario: Navigate to Vitals screen from HOPI
    Given The user is on the HOPI screen
    When The user clicks Next on HOPI screen
    Then The user should navigate to the Vitals screen

  @TC7
  Scenario: Add vitals
    Given The user is on the Vitals screen
    When The user add vitals
    Then The vitals should be saved successfully

  @TC8
  Scenario: Navigate to Diagnosis screen from Vitals
    Given The user is on the Vitals screen
    When The user clicks Next on Vitals screen
    Then The user should navigate to the Diagnosis screen

  @TC9
  Scenario: Add diagnosis with type
    Given The user is on the Diagnosis screen
    When The user searches and adds a diagnosis
    Then The diagnosis should be added to the list

  @TC10
  Scenario: Remove added diagnosis
    Given The user is on the Diagnosis screen
    When The user removes a diagnosis using the cross icon
    Then Diagnosis should be removed from the list
    When The user clicks the Clear All button
    Then All diagnoses should be removed

  @TC11
  Scenario: Add custom and quick type diagnosis
    Given The user is on the Diagnosis screen
    When The user adds a custom diagnosis
    Then Custom diagnosis should be added
    When The user adds diagnosis using quick type
    Then It should be added

  @TC12
  Scenario: View diagnosis history and info
    Given The user is on the Diagnosis screen
    When The user clicks on diagnosis history
    Then Relevant diagnosis information should be displayed

  @TC13
  Scenario: Navigate to Prescribe screen from Diagnosis
    Given The user is on the Diagnosis screen
    When The user clicks Next on Diagnosis screen
    Then The user should navigate to the Prescribe screen

  @TC14
  Scenario: Add medicines
    Given The user is on the Prescribe screen
    When The user searches and adds medicines
    Then The medicines should be added to the list

  @TC15
  Scenario: Remove added medicines
    Given The user is on the Prescribe screen
    When The user removes a medicine using the cross icon and Clear All button
    Then The medicines should be removed

  @TC16
  Scenario: Add custom and quick type medicines
    Given The user is on the Prescribe screen
    When The user adds a custom medicine and from quick type
    Then The medicine should be added successfully

  @TC17//NeedToCheck
  Scenario: Add medicines using templates
    Given The user is on the Prescribe screen
    When A template is available and displayed
    Then The user should able to apply and medicines should be added

  @TC18
  Scenario: Add medicine from prescription history
    Given The user is on the Prescribe screen
    When The user opens prescription history and selects a medicine
    Then The medicine should be added

  @TC19
  Scenario: Navigate to Investigation screen from Prescribe
    Given The user is on the Prescribe screen
    When The user clicks Next from Prescribe screen
    Then The user should navigate to the Investigation screen

  @TC20
  Scenario: Add investigation tests
    Given The user is on the Investigation screen
    When The user searches and adds investigation tests
    Then The tests should be added to the investigation

  @TC21
  Scenario: Remove added tests
    Given The user is on the Investigation screen
    When The user removes a test or clicks Clear All
    Then The tests should be removed

  @TC22
  Scenario: Add custom and quick type tests
    Given The user is on the Investigation screen
    When The user adds a custom test or from quick type
    Then The test should be added

  @TC23_NeedToCheck
  Scenario: Add tests using templates
    Given The user is on the Investigation screen
    When A test template is available and selected
    Then The template tests should be added

  @TC24
  Scenario: Navigate to Patient Instructions screen from Investigation
    Given The user is on the Investigation screen
    When The user clicks Next from Investigation screen
    Then the user should navigate to Patient Instructions screen

  @TC25
  Scenario: Add and remove patient instructions
    Given The user is on the Patient instructions screen
    When The user adds a patient instruction
    Then It should appear in the list
    When The user removes it
    Then Instructions should be removed

  @TC26
  Scenario: Navigate to Overview screen from Patient Instructions
    Given The user is on the Patient instructions screen
    When The user clicks Next from Patient Instructions screen
    Then The user should navigate to the Overview screen

  @TC27
  Scenario: Add and remove allergies in overview
    Given The user is on the Overview screen
    When The user adds Drug, Food, and Environment allergies
    Then Allergies should be visible
    When The user removes them
    Then Allergies should be removed

  @TC28
  Scenario: Add and remove Problem list in overview
    Given The user is on the Overview screen
    When The user adds a problem list item
    Then Problem list should be visible
    When The user removes problem list
    Then Problem list item should be removed

  @TC29
  Scenario: Add and remove medications in overview
    Given The user is on the Overview screen
    When The user adds a medication
    Then Medication should appear in the list
    When The user removes medication
    Then Medication should be removed

  @TC30
  Scenario: Add and remove family history
    Given The user is on the Overview screen
    When The user adds family history
    Then Family history should appear
    When The user removes family history
    Then Family history should be removed

  @TC31
  Scenario: Add and remove past surgical history
    Given The user is on the Overview screen
    When The user adds past surgical history
    Then Past surgical history items should appear
    When The user removes past surgical history items
    Then Past surgical history items should be removed

  @TC32
  Scenario: Add and remove past medical conditions
    Given The user is on the Overview screen
    When The user adds past medical conditions
    Then Past medical conditions item should appear
    When The user removes past medical conditions items
    Then Past medical conditions items should be removed

  @TC33
  Scenario: Add or update social history
    Given The user is on the Overview screen
    When The user adds or updates social history
    Then The updates should be saved

  @TC34
  Scenario: Navigate to Assessment screen from Overview
    Given The user is on the Overview screen
    When The user clicks Next from Overview screen
    Then The user should navigate to the Assessment screen

  @TC35
  Scenario: Add assessments
    Given The user is on the Assessments screen
    When The user adds assessments during screening
    Then They should saved properly

  @TC36
  Scenario: Navigate to Refer a Doctor from Assessment
    Given The user is on the Assessments screen
    When The user clicks Next from Assessment screen
    Then The user should navigate to Refer a Doctor screen

  @TC37
  Scenario: Add refer doctor details
    Given The user is on the Refer a doctor screen
    When The user enters refer doctor details
    Then Details should be saved

  @TC38
  Scenario: Navigate to Follow-up screen from Refer a Doctor
    Given The user is on the Refer a doctor screen
    When The user clicks Next from Refer a Doctor screen
    Then The user should navigate to Follow-up screen

  @TC39
  Scenario: Preview prescription PDF
    Given The user is on the Patient Screening page
    When The user clicks on PreviewRx
    Then The PDF should open and display valid information

  @TC40
  Scenario: End encounter with lab, pharmacy and language
    Given The user is on the Patient Screening page
    When The user click on End encounter
    And The user selects lab, pharmacy, and language and ends encounter
    Then The encounter should be marked as completed

  @TC41
  Scenario: End encounter with HOPI and prescription only
    Given The user is present on dashboard
    When The user clicks on Appointments
    And Select the appointments
    And The user initiates an appointment
    When The user provides only HOPI and prescription
    And Ends the encounter completely
    Then The encounter should be marked as completed

  @TC42
  Scenario: End encounter without prescription
    Given The user is present on dashboard
    When The user clicks on Appointments
    And Select the appointments
    And The user initiates an appointment
    When The user fills details but skips prescription
    And Ends the encounter completely
    Then The encounter should be marked as completed