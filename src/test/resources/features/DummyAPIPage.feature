Feature: DummyAPI Testing
  As a QA Engineer
  I want to test the DummyAPI endpoints
  So that I can validate the API functionality

  Background:
    Given the DummyAPI base URL is set

  @positive
  Scenario: Get all users successfully
    When I send a GET request to "/user"
    Then the response status should be 200
    And the response should contain a list of users

  @positive
  Scenario: Get a specific user by ID
    When I send a GET request to "/user/60d0fe4f5311236168a109d4"
    Then the response status should be 200
    And the response should contain user details with id "60d0fe4f5311236168a109d4"

  @positive
  Scenario: Create a new user successfully
    Given I have valid user data:
      | firstName | lastName | email              |
      | John      | Doe      | john.doe@test.com  |
    When I send a POST request to "/user/create" with the user data
    Then the response status should be 200
    And the response should contain the created user details

  @positive
  Scenario: Update user information
    Given I have user update data:
      | firstName | lastName |
      | Jane      | Smith    |
    When I send a PUT request to "/user/60d0fe4f5311236168a109d4" with the update data
    Then the response status should be 200
    And the response should contain the updated user information

  @positive
  Scenario: Get all posts successfully
    When I send a GET request to "/post"
    Then the response status should be 200
    And the response should contain a list of posts

  @negative
  Scenario: Get user with invalid ID returns error
    When I send a GET request to "/user/invalid-id-12345"
    Then the response status should be 400
    And the response should contain an error message