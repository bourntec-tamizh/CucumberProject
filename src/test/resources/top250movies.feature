#feature to check top 250 movies
Feature: check whether given movie is in IMDB top 250 list or not
  Scenario: Check a single movie
    Given  I am in IMDB website
    When click top 250 movies
    Then check whether "Schindler's List" is present in the list or not