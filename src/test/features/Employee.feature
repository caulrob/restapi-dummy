#language:en 

Feature: Manage employees

    Background: Use endpoint employees
        Given  employees endpoint

    Scenario: request all employees
        When i request all employees
        Then I should get a 200 status

    Scenario: delete an employee
        When delete an employee
        Then employee was delete
