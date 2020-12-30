# Feature file for CatApp home page
Feature: Cat App home page works

# Background: Given I am on the CatApp home page

Scenario Outline: Logging in works
	Given I am on the BetterReads home page
	When I enter "<username>" and "<password>" to log in
	And I click the login button
	Then the shelf header should contain "<username>"

		
	
Scenario Outline: The nav bar works
	Given I am on the CatApp home page
	And I am logged in
	When I click on the "<search>" text 
    The search box appears