Feature: Pet Service API Tests

  Scenario: Create a pet
    Given a pet with name "TestPet" and status "pending"
    When I send a request to create the pet
    Then the response status code should be 200
    And the pet name should be "TestPet"

  Scenario: Find created pet in the list
    Given a pet with name "TestPet" and status "pending" exists
    When I retrieve pets by status "pending"
    Then the list should contain a pet with name "TestPet"

  Scenario: Delete a pet
    Given a pet with ID 1 exists
    When I delete the pet with ID 1
    Then the response status code should be 200