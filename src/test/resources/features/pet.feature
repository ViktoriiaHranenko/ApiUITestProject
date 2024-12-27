Feature: Pet Service API Tests

  Scenario Outline: Create a pet
    When I send a request to create the pet with id <id> name <name> and status <status>
    Then the response status code should be <statusCode>
    And the pet name should be <name>
    Examples:
      | id     | name      | status    | statusCode |
      | "0938" | "TestPet" | "PENDING" | 200        |
