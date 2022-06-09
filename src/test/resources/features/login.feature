Feature: Login Feature

  Scenario: 101001 Scenario Success Login
    * Open Application
    * Login Account "17750223962","123456"
    * Wait 5 seconds
    * // to-do Sign Out Account

  Scenario: 001002 Scenario Login Invalid IdentitiyNumber
    * Open Application
    * Click on "home_login_button" element
    * Find element by "citizenId_input" and send keys "12345678911"
    * Find element by "password_input" and send keys "123456"
    * Click on "login_button" element
    * "invalidTcknVkn_message" verify if element exists
    * Wait 5 seconds

    * // to-do tamam butonuna bas ve back butonuna tıkla
    * Quit Appium Driver

  Scenario: 001003 Scenario Login Invalid Password
    * Open Application
    * Click on "home_login_button" element
    * Wait 1 seconds
    * Find element by "citizenId_input" and send keys "17750223962"
    * Wait 1 seconds
    * Find element by "password_input" and send keys "101010"
    * Wait 1 seconds
    * Click on "login_button" element
    * Wait 1 seconds
    * "invalidPassword_message" verify if element exists
    * Wait 5 seconds

    * // to-do tamam butonuna bas ve back butonuna tıkla
    * Quit Appium Driver

  Scenario: 001004 Scenario Login Unregistered User
    * Open Application
    * Click on "home_login_button" element
    * Wait 1 seconds
    * Find element by "citizenId_input" and send keys "17750223962"
    * Wait 1 seconds
    * Find element by "password_input" and send keys "123456"
    * Wait 1 seconds
    * Click on "login_button" element
    * Wait 1 seconds


    * "invalidPassword_message" verify if element exists
    * Wait 5 seconds

    * // to-do tamam butonuna bas ve back butonuna tıkla
    * Quit Appium Driver