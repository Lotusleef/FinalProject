@api
Feature: DummyAPI CRUD and Tags

  Background:
    Given API base is DummyAPI

  Scenario: Get user by id (stable)
    When I pick an existing user id from list @api
    And I get user by that id @api
    Then response status should be 200
    And response should contain field "id"

  Scenario: Create, Update, and Delete user
    When I create a new user @api
    Then response status should be 200
    And response should contain field "id"

    When I update the user with new firstName "RayUpdated" @api
    Then response status should be 200
    And response should contain field "firstName"

    When I delete the user @api
    Then response status should be 200
    And response should contain field "id"

  Scenario: Get tag list
    When I get tag list @api
    Then response status should be 200
    And response list size "data" should be > 0
