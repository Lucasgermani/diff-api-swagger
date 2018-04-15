Feature: Comparator class unit tests

  So that I can compare two string
  As a API user
  I want know the offsets and length of the differences

  # Base64String -> weas, weas
  Scenario: Left and Right Value are EQUAL
    Given I am sending an Entry with ID 33 and "d2Flcw==" on the left and "d2Flcw==" on the right
    When Comparing them
    Then the result should be "EQUAL"

  # Base64String -> weas, w43s
  Scenario: Left and Right Value are DIFFERENT
    Given I am sending an Entry with ID 78 and "d2Flcw==" on the left and "dzQzcw==" on the right
    When Comparing them
    Then the result should be "DIFFERENT"

  # Base64String -> weas, wearewaes
  Scenario: Left and Right Value are DIFFERENT_SIZE
    Given I am sending an Entry with ID 91 and "d2Flcw==" on the left and "d2VhcmV3YWVz" on the right
    When Comparing them
    Then the result should be "DIFFERENT_SIZE"

  # Base64String -> weas, w3as
  Scenario: Left and Right Value are DIFFERENT and have 1 difference
    Given I am sending an Entry with ID 77 and "d2Vhcw==" on the left and "dzNhcw==" on the right
    When Comparing them
    Then the result should be "DIFFERENT"
    And diff count should be equals to 1
    And should contains diff with offset 1 and lenght 2

  # Base64String -> wearewaes, w3ar3wa3s
  Scenario: Left and Right Value are DIFFERENT and have multiple differences
    Given I am sending an Entry with ID 45 and "d2VhcmV3YWVz" on the left and "dzNhcjN3YTNz" on the right
    When Comparing them
    Then the result should be "DIFFERENT"
    And diff count should be equals to 3
    And should contains diff with offset 1 and lenght 2
    And should contains diff with offset 5 and lenght 2
    And should contains diff with offset 9 and lenght 2
