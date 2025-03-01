@run
Feature: User Management

  As a user
  I want to be able to add or delete the users

  @TC1
  Scenario: Verify default user displayed on the users list screen
    Given The user navigates to the users list screen
    Then The logged-in admin should be displayed in the users list by default
    And No other users should be shown if no additional admins, doctors, or staff are available

  @TC2
  Scenario: Verify user can add a doctor through users
    Given The user is on the users list screen
    When The user clicks on Add button and selects Doctor
    And The user enters required doctor details and submits the form
    Then The doctor should be added successfully
    And The newly added doctor should be visible in the users list

  @TC3
  Scenario: Verify user can add staff through users
    Given The user is on the users list screen
    When The user clicks on Add button and selects Staff
    And The user enters required staff details and submits the form
    Then The staff should be added successfully
    And The newly added staff should be visible in the users list

  @TC4
  Scenario: Verify user can add an admin through users
    Given The user is on the users list screen
    When The user clicks on Add button and selects Staff
    And The user enters required admin details and submits the form
    Then The admin should be added successfully
    And The newly added admin should be visible in the users list

  @TC5
  Scenario: Verify user can see the list of added users
    Given The user is on the users list screen
    Then The list of added users should be displayed

  @TC6
  Scenario: Verify user can search added users and view results accordingly
    Given The user is on the users list screen
    When The user enters a name in the search bar
    Then The matching users should be displayed in the results

  @TC7
  Scenario: Verify default message when searched user is not available
    Given The user is on the users list screen
    When The user searches for a non-existing user
    Then The message No users found should be displayed

  @TC8
  Scenario: Verify edit option is not displayed for the logged-in user
    Given The user is on the users list screen
    Then The edit option for logged in user should not be available from user list

  @TC9
  Scenario: Verify user can edit doctor privileges
    Given The user is on the users list screen
    When The user selects a doctor and clicks on Edit Privileges
    And The user modifies the doctor privileges and saves the changes
    Then The updated doctor privileges should be saved successfully

  @TC10
  Scenario: Verify user can edit staff privileges
    Given The user is on the users list screen
    When The user selects a staff member and clicks on Edit Privileges
    And The user modifies the staff privileges and saves the changes
    Then The updated staff privileges should be saved successfully

  @TC11
  Scenario: Verify user cannot edit admin privileges
    Given The user is on the users list screen
    When The user selects an admin and clicks on Edit Privileges
    And The user cannot modifies the privileges of admin

  @TC12
#  Scenario: Verify user can delete Doctor or Staff
#    Given The user is on the users list screen
#    When The user selects a Doctor or Staff and clicks Delete
#    And Confirms the deletion
#    Then The selected user should be removed from the users list

  @TC13
  Scenario: Verify user can delete an admin when multiple admins are available
    Given There are multiple admins in the clinic and displayed on UserList
    When The user selects an admin and clicks Delete
    And Confirms the deletion
    Then The admin should be removed from the users list

  @TC14
  Scenario: Verify user cannot delete the only one admin of the clinic
    Given There is only one admin in the clinic and displayed on UserList
    When The user attempts to delete the admin
    Then The system should display an error message At least one admin is required

  @TC15
  Scenario: Verify doctor can log in independently and view details correctly
    Given The doctor logs into the application independently
    Then The doctor's dashboard should be displayed correctly
    And The doctor's details should be visible and accurate

  @TC16
  Scenario: Verify staff can log in independently and view details correctly
    Given The staff logs into the application independently
    Then The staff's dashboard should be displayed correctly
    And The staff's details should be visible and accurate

  @TC17
  Scenario: Verify admin can log in independently and view details correctly
    Given The admin logs into the application independently
    Then The admin's dashboard should be displayed correctly
    And The admin's details should be visible and accurate
