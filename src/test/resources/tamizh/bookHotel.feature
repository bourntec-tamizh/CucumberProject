@hotel
Feature: This features books hotel


  Scenario Outline:book with invalid check-in date
    When User open united home page
    And User selects Book tab
    And User selects Hotel tab
    When Enter destination "<valid destination>"
    And Enter checkin "<invalid check-in>" date
    Then "Please enter a valid checkin date." error message should be shown
    Examples:
      | valid destination          | invalid check-in |
      | Chennai, Tamil Nadu, India | May 13           |

  Scenario Outline:book with invalid check-out date
    When User open united home page
    And User selects Book tab
    And User selects Hotel tab
    When Enter destination "<valid destination>"
    And Enter checkin "<valid check-in>" date
    And Enter checkout "<invalid check-out>" date
    Then "Please enter a valid checkout date." error message should be shown
    Examples:
      | valid destination          | valid check-in | invalid check-out |
      | Chennai, Tamil Nadu, India | May 20         | May 11            |

  Scenario Outline:book with length of stay greater than 28
    When User open united home page
    And User selects Book tab
    And User selects Hotel tab
    When Enter destination "<valid destination>"
    And Enter checkin "<valid check-in>" date
    And Enter checkout "<valid check-out>" date where the no of days between two dates is greater than 28
    Then "Please enter a length of stay of 28 day or less." error message should be shown
    Examples:
      | valid destination          | valid check-in | valid check-out |
      | Chennai, Tamil Nadu, India | May 29         | Jun 30          |


  Scenario Outline:book with invalid destination
    When User open united home page
    And User selects Book tab
    And User selects Hotel tab
    When Enter destination "<invalid destination>"
    And Enter checkin "<valid check-in>" date
    And Enter checkout "<valid check-out>" date
    And Enter no of rooms "<number of rooms>"
    And Press find hotels
    Then a new tab opens with error message "Sorry, we couldn't find the page you were looking for" or "This page isn’t working"
    Examples:
      | invalid destination | valid check-in | valid check-out | number of rooms |
      | xhwjdhsa678         | May 15         | Jun 09          | 4               |


  Scenario Outline:successful hotel booking
    When User open united home page
    And User selects Book tab
    And User selects Hotel tab
    When Enter destination "<valid destination>"
    And Enter checkin "<valid check-in>" date
    And Enter checkout "<valid check-out>" date
    And Enter no of rooms "<number of rooms>"
    And Press find hotels
    Then a list of hotels with correct dates and location should be shown
    Examples:
      | valid destination          | valid check-in | valid check-out | number of rooms |
      | Chennai, Tamil Nadu, India | May 15         | Jun 07          | 3               |
  
  
