@run
Feature: Clinic Onboarding

  As a user
  I want to be able to onboard the clinic successfully

  @TC1
  Scenario: Verify user able to see the login screen with the "Sign Up" option
    Given The user has launched the application
    When The application loads successfully and login screen displayed
    Then The login screen should contain the Sign Up option

  @TC2
  Scenario: Verify navigation to the signup screen after clicking on the "Sign Up" button
    Given The user is on the login screen
    When The user clicks on the Sign Up button
    Then The user should be navigated to the signup screen

  @TC3
  Scenario: Verify user can enter mobile number and clinic registration number on the sign-up screen
    Given The user is on the sign-up screen
    When The user enters a valid mobile number and clinic registration number
    Then The entered details should be accepted

  @TC4
  Scenario: Verify navigation to the Personal Information screen after OTP verification
    Given The user is on the verifyOTP screen
    When The user verify the OTP and click on Verify OTP
    Then The user should be navigated to the Personal Information screen

  @TC5
  Scenario: Verify user can enter all details in the Personal Information screen
    Given The user is on the Personal Information screen
    When The user enters all required details
    Then The details should be accepted successfully

  @TC6
  Scenario: Verify navigation to the Clinic Details screen from the Personal Information screen
    Given The user has entered valid personal details
    When The user clicks on the continue button
    Then The user should be navigated to the Clinic information screen

  @TC7
  Scenario: Verify user can enter clinic details on the Clinic Information screen
    Given The user is on the Clinic information screen
    When The user enters all required clinic details
    Then The clinic details should be accepted successfully

  @TC8
  Scenario: Verify navigation to the Address screen from the Clinic Information screen
    Given The user has entered required valid clinic details
    When The user clicks on the continue button
    Then The user should be navigated to the Address screen

  @TC9
  Scenario: Verify user can enter address details and complete the onboarding process
    Given The user is on the Address screen
    When The user enters all required address details
    And Clicks on the submit button
    Then The onboarding process should be completed successfully
    And account status is pending and user able to logout

