Feature: The resume authoring process
  
  As a developer of the web site,
  I want to ensure the resume entry process is fully functional
  So that I can move on with other pressing tasks

  Scenario: create a resume
    Given a login of "domyorker" and "up4life"
    When I log in
    And go to "Manage Resumes"
    And create a resume titled "Junior Web Developer"
    Then I should see "Junior Web Developer" in "main"

  Scenario: partially update a resume
    Given a login of "domyorker" and "up4life"
    When I log in
    And go to "Manage Resumes"
    And edit the resume titled "Junior Web Developer"
    And I enter my summary text
    And submit the changes by clicking "btnSubmit"
    Then I should see "success" in "main"
    
   
