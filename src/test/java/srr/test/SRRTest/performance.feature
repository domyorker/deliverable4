Feature: Measuring performance metrics for collection and reporting
  
  
  As the lead systems architect for the student resume repository
  I want to collect preliminary performance metrics
  So that we can gauge whether to continue or revise our current model

  Scenario: Registration performance
    Given I start the performance test
    When I register "200" "test" accounts
    And I stop the performance test
    Then display performance results

  Scenario: Login performance
    Given I start the performance test
    Given a login of "domyorker" and "up4life"
    And I login "100" times
    And I stop the performance test
    Then display performance results

  Scenario: Resume Creation performance
    Given a login of "domyorker" and "up4life"
    And I log in
    Given I start the performance test
    And create "100" "Temporary" resumes
    Then display performance results
