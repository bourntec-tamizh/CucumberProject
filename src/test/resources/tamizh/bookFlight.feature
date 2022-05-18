@flight
Feature: flight booking

  Scenario Outline: successful roundtrip flight booking
    Given  User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And enter from city "<fromCity>"
    And enter to city"<toCity>"
    And Enter from date "<fromDate>"
    And Enter to date "<toDate>"
    And Select travelers "<travelers>" "<passengerCount>"
    And select class "<class>"
    And Click find flights
    Then a list of flight details should be shown
    Examples:
      | journeyType | fromCity    | toCity         | fromDate | toDate | travelers        | passengerCount | class           |
      | Roundtrip   | Chicago ORD | Charleston CHS | Jun 08   | Jul 07 | Adults (18 - 64) | 5              | Premium economy |
  # | Roundtrip   | Anchorage ANC | Telluride TEX  | Jun 08   | Jul 07 | Seniors (65+)    | 4              | Business        |

  Scenario Outline: successful oneway flight booking
    Given User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And enter from city "<fromCity>"
    And enter to city"<toCity>"
    And Enter from date "<fromDate>"
    And Select travelers "<travelers>" "<passengerCount>"
    And select class "<class>"
    And Click find flights
    Then a list of flight details should be shown
    Examples:
      | journeyType | fromCity      | toCity    | fromDate | travelers     | passengerCount | class           |
      | oneway      | Texarkana TXK | Miami MIA | Jul 23   | Seniors (65+) | 5              | Premium economy |
  #  | Roundtrip   | Anchorage ANC | Telluride TEX  | Jun 08   | Seniors (65+)    | 4              | Business        |

  Scenario Outline: clicking find flights without any details
    Given User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And Click find flights
    Then ensure below error messages are shown
      | Sorry, no results have been found. Please enter a different origin location or expand your search area |
  #| Please enter a valid origin | Please enter a valid destination | Please enter a valid departure date |
    Examples:
      | journeyType |
      | oneway      |
      | Roundtrip   |

  Scenario Outline: entering invalid departure date
    Given User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And Enter from date "<invalid fromDate>"
    And Enter blank value in toDate field
    Then ensure below error messages are shown
      | Please enter a valid departure date |
    Examples:
      | journeyType | invalid fromDate |
      | oneway      | Jan 20           |
      | Roundtrip   | Feb 03           |

  Scenario Outline: entering invalid return date
    Given  User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And Enter from date "<valid fromDate>"
    And Enter to date "<invalid toDate>"
    And click and press Enter in fromDate field
    Then ensure below error messages are shown
      | Please enter a valid return date |
    Examples:
      | journeyType | valid fromDate | invalid toDate |
      | oneway      | Oct 03         | Apr 05         |
      | Roundtrip   | Oct 08         | Apr 06         |


  Scenario Outline: book with flexible dates option only
    Given User open united home page
    And User selects Book tab
    When user select journeytype "<journeyType>"
    And select flexible date checkbox
    And enter from city "<fromCity>"
    And enter to city"<toCity>"
    And select flexible month
    And select flexible days if journeytype "<journeyType>" is roundtrip
    And Select travelers "<travelers>" "<passengerCount>"
    And select class "<class>"
    And Click find flights
    Then a list of flight details should be shown
    Examples:
      | journeyType | fromCity      | toCity    | travelers     | passengerCount | class           |
      | oneway      | Texarkana TXK | Miami MIA | Seniors (65+) | 5              | Premium economy |
      | roundtrip   | Texarkana TXK | Miami MIA | Seniors (65+) | 5              | Premium economy |


