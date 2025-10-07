@web
Feature: SauceDemo Login and Checkout

  Scenario: Login with valid credentials
    Given I open SauceDemo login page @web
    When I login with username "standard_user" and password "secret_sauce" @web
    Then I should be on inventory page @web

  Scenario: Login with invalid credentials
    Given I open SauceDemo login page @web
    When I login with username "standard_user" and password "wrongpass" @web
    Then I should see error message contains "Username and password do not match" @web

  Scenario: Login without username/password
    Given I open SauceDemo login page @web
    When I login with username "" and password "" @web
    Then I should see error message contains "Username is required" @web

  Scenario: End-to-end login and checkout
    Given I am logged in as standard_user @web
    When I add products and proceed to checkout @web
    And I complete checkout info "Ray" "Han" "10010" @web
    Then Order should be completed and I can return to homepage @web

  Scenario: Logout after successful
    Given I am logged in as standard_user @web
    When I logout @web
    Then I should be back on login page @web
