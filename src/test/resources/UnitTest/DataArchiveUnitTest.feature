Feature: DataArchive class unit tests

  So that ValuesEntry can be stored to later use
  As a API user
  I want to store and retrieve data from DataArchive class

  Scenario: Add left value and retrieve using the same ID
    Given I am sending "left" data to be stored with ID 45 and Data "d2Flcw=="
    When I tryed to retrieve the data with id 45
    Then should return the exact data "d2Flcw==" on "left" side

  Scenario: Add right value and retrieve using the same ID
    Given I am sending "right" data to be stored with ID 63 and Data "bmV0aGVybGFuZHM="
    When I tryed to retrieve the data with id 63
    Then should return the exact data "bmV0aGVybGFuZHM=" on "right" side

  Scenario: Add right and left value and retrieve using the same ID
    Given I am sending "right" data to be stored with ID 54 and Data "YW1zdGVyZGFu"
    And I am sending "left" data to be stored with ID 54 and Data "bmV0aGVybGFuZHM="
    When I tryed to retrieve the data with id 54
    Then should return the exact data "bmV0aGVybGFuZHM=" on "left" side
    And should return the exact data "YW1zdGVyZGFu" on "right" side