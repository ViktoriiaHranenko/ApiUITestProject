Feature: Pet Service API Tests

  Scenario Outline: Create a pet
    When I create the pet with id <id> name <name> and status <status>
    Then the response status code should be <statusCode>
    And the pet name should be <name>

    Examples:
      | id      | name               | status      | statusCode |
      | "09381" | "pendingTestPet"   | "PENDING"   | 200        |
      | "09382" | "availableTestPet" | "AVAILABLE" | 200        |
      | "09383" | "soldTestPet"      | "SOLD"      | 200        |
