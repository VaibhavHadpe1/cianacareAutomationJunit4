@run
Feature: User Login

  As a registered user
  I want to be able to log in to cianacare
  So that I can access different features of cianacare

  Scenario: User successfully logs in with valid credentials
    Given the user has launched cianacare application and present on the login page
    When user enter a valid mobile number and click Continue
    And enter the correct OTP and click Verify OTP
    Then user should be redirected to the switch clinic screen and select a clinic
    And be successfully logged in and present on the dashboard
    Then user loggedout from the cianacare application
