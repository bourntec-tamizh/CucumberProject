@package
Feature: This books packages
  Scenario Outline: successful package booking
    Given User open united home page
    And User selects Book tab
    When user select package tab
    When Select package type "<packageType>"
    And if package type "<packageType>" is Flight and car, ensure Rooms field is invisible
    And enter From in package  "<valid fromCity>"
    And enter To in package"<valid toCity>"
    And Enter from date "<valid fromDate>"
    And Enter to date "<valid toDate>"
    And Select travelers "<travelers>" "<passengerCount>"
  #max 4 rooms and min 1 room
    And  select room in package "<no of rooms>"
    And Click Find Trips
    Then a result page opens in new tab and contains details
    Examples:
      | packageType           | valid fromCity | valid toCity | valid fromDate | valid toDate | travelers        | passengerCount | no of rooms |
      | Flight and hotel      | Washington, DC | Houston, TX  | May 19         | May 21       | Adults (18 - 64) | 3              | 1           |
      | Flight, hotel and car | Washington, DC | Houston, TX  | May 19         | May 21       | Adults (18 - 64) | 3              | 1           |
      | Flight and car        | Washington, DC | Houston, TX  | May 19         | May 21       | Adults (18 - 64) | 3              | NA          |

  Scenario Outline: clicking Find trips without any details
    Given User open united home page
    And User selects Book tab
    When user select package tab
    When Select package type "<packageType>"
    And Click Find Trips
    Then ensure below error messages are shown
      | Please enter a valid origin | Please enter a valid destination | Please enter a valid departure date |
    Examples:
      | packageType           |
      | Flight and hotel      |
      | Flight, hotel and car |
      | Flight and car        |


  Scenario Outline: entering invalid departure date
    Given User open united home page
    And User selects Book tab
    When user select package tab
    When Select package type "<packageType>"
    And enter From in package  "<valid fromCity>"
    And enter To in package"<valid toCity>"
    And Enter from date "<invalid fromDate>"
    And Enter to date "<valid toDate>"
    And click and press Enter in fromDate field
    Then ensure below error messages are shown
      | Please enter a valid departure date |
    Examples:
      | packageType           | valid fromCity | valid toCity | invalid fromDate |valid toDate|
      | Flight and hotel      | Washington, DC | Houston, TX  | May 09           |Jun 14      |
      | Flight, hotel and car | Washington, DC | Houston, TX  | May 06           |Jun 14      |
      | Flight and car        | Washington, DC | Houston, TX  | Apr 20           |Jun 14      |


  Scenario Outline: entering invalid return date
    Given User open united home page
    And User selects Book tab
    When user select package tab
    When Select package type "<packageType>"
    And enter From in package  "<valid fromCity>"
    And enter To in package"<valid toCity>"
    And Enter from date "<valid fromDate>"
    And Enter to date "<invalid toDate>"
    Then ensure below error messages are shown
      | Please enter a valid return date |
    Examples:
      | packageType           | valid fromCity | valid toCity | valid fromDate | invalid toDate |
      | Flight and hotel      | Washington, DC | Houston, TX  | May 29         | gfcfch         |
      | Flight, hotel and car | Washington, DC | Houston, TX  | May 30         | tsrdyr12       |
      | Flight and car        | Washington, DC | Houston, TX  | Jun 20         | Feb 12         |


