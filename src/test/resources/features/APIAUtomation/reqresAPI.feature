@API
Feature: reqre.in API testing

  Scenario: List Users
    When I submit GET request for listUserAPI
    Then I verify status code is 200
    Then I get a list of users

  Scenario: Single User
    When I submit GET request for singleUserApi
    Then I verify status code is 200
    Then I get a single user data

  Scenario: invalid user
    When I submit GET request for invalidUserApi
    Then I verify status code is 404

  Scenario Outline: post user
    When I submit POST request with details "<name>" "<job>"
    Then I verify status code is 201
    And verify response contains correct posted details "<name>" "<job>"
    Examples:
      | name     | job    |
      | morpheus | leader |

  Scenario Outline: put request
    When I submit PUT request with details "<id>" "<name>" "<job>"
    Then I verify status code is 200
    And verify response contains correct put details "<name>" "<job>"
    Examples:
      | id | name     | job           |
      | 2  | morpheus | zion resident |

  Scenario Outline: delete user
    When I submit DELETE request for id "<id>"
    Then I verify status code is 204
    Examples:
      | id |
      | 2  |

  Scenario Outline: Registration <type>
    When I submit register request with email "<email>" and password "<password>"
    Then verify statusCode is "<statusCode>" and error message is "<errorMessage>"
    Examples:
      | email              | password   | statusCode | errorMessage                 |type        |
      | eve.holt@reqres.in | pistol     | 200        |                              |successful  |
      | sydney@fife        |            | 400        | Missing password             |unsuccessful|
      |                    | gkgjhkk    | 400        | Missing email or username    |unsuccessful|
      |                    |            | 400        | Missing email or username    |unsuccessful|

  Scenario Outline: Login <type>
    When I submit login request with email "<email>" and password "<password>"
    Then verify statusCode is "<statusCode>" and error message is "<errorMessage>"
    Examples:
      | email              | password   | statusCode       | errorMessage                 |type        |
      | eve.holt@reqres.in | cityslicka | 200              |                              |successful  |
      | peter@klaven       |            | 400              | Missing password             |unsuccessful|
      |                    | gkgjhkk    | 400              | Missing email or username    |unsuccessful|
      |                    |            | 400              | Missing email or username    |unsuccessful|


  Scenario: delayed response
    When I submit GET request for listUserApi with 3 seconds delay
    Then I verify response time is "3" seconds