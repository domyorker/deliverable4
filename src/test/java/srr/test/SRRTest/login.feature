Feature: The login process

  As an administrator of the website,
  I want to ensure the login process is fully functional
  So that only authorized users of the website have access to restricted functions

  Scenario: Valid Login
    Given a login of "domyorker" and "up4life"
    When I log in
    Then I should be logged in

  Scenario: Blank password field
    Given a login of "domyorker" and ""
    When I log in
    Then I should see "Invalid" in "errorDIV"

  Scenario: Blank username field
    Given a login of "" and "up4life"
    When I log in
    Then I should see "Invalid" in "errorDIV"

  Scenario: Both username and password blank
    Given a login of "" and ""
    When I log in
    Then I should see "Invalid" in "errorDIV"

  Scenario: Correct login with CAPS inversion
    Given a login of "DOMYORKER" and "UP4LIFE"
    When I log in
    Then I should see "Invalid" in "errorDIV"
