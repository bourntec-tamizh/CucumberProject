@car
Feature: This feature books cars

  Scenario Outline: book with same drop off location and driver age>=25
    When User open united home page
    And User selects Book tab
    When Select car tab
    And Input pickup location "<pickup location>"
    And Select return car to same location
    Then ensure Drop off location input field is disabled
    When enter Pick up date "<Pick up date>"
    And enter drop off date "<drop off date>"
    And Select primary driver is 25 or older
    Then ensure Age field is disabled
    When select pickup time
    And select drop off time
    And Click find cars
    Then list of cars available shows in new tab
    Examples:
      | pickup location                              | Pick up date | drop off date |
      | Washington                                   | May 18       | Jun 08        |

  Scenario Outline: book with different drop off location and driver age<25
    When User open united home page
    And User selects Book tab
    When Select car tab
    And Input pickup location "<pickup location>"
    And unselect return car to same location
    Then ensure Drop off location input field is enabled
    And Input drop off location "<drop off location>"
    When enter Pick up date "<Pick up date>"
    And enter drop off date "<drop off date>"
    And unselect primary driver is 25 or older
    Then ensure Age field is enabled
    When enter driver age "<driver age>"
    And select pickup time
    And select drop off time
    And Click find cars
    Then list of cars available shows in new tab

    Examples:
      | pickup location                              | drop off location                      | Pick up date | drop off date | driver age |
      | Washington                                   | Virginia                               | May 18       | May 25        | 22         |


  Scenario: book with all blank details
    When User open united home page
    And User selects Book tab
    When Select car tab
    And Click find cars
    Then ensure below error messages are shown
      | Please enter a valid pickup location |
      | Please enter a valid pickup date     |


