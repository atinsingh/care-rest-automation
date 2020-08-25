# new feature
# Tags: optional

Feature: Testing Course Info API

  Scenario Outline: GET-Retrieves the course info with invalid id.
    Given user enter the invalid course id in api path.
    When User calls course-info with "<info_id>" as path param
    Then status code should be <status_code> in the response
    And response code should be "<error>" in response body
    And response type should be "<error_desc>" in response body
    Examples:
      | info_id | status_code |error |error_desc|
      | 100112  | 400         |Bad Request  | Invalid course info id|

  Scenario Outline: GET-Retrieves the course info with valid id.
    Given user enter the valid course id in api path.
    When User calls course-info with "<info_id>" as path param
    Then status code should be <status_code> in the response
    Examples:
      | info_id |status_code |
      | 5f0b1d8216b5b97e87391483  | 200 |
      | 5f0b3da979202c7fd738a7ee  | 200 |
