Feature: Role-based access control

  Scenario Outline: Role-based endpoint access
    Given I have role "<role>"
    When I send a <method> request to "<endpoint>"
    Then I should receive status code <status>

    Examples:
      | role    | method | endpoint               | status |
      | student | GET    | /class/students        | 200    |
      | teacher | POST   | /class/students        | 200    |
      | teacher | DELETE | /class/students/10     | 200    |
