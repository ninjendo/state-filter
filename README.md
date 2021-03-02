# Chick-fil-A Coding Challenge
### Prerequisites
* Java 8 or later

## Instructions
There is a single unit test in this project called `AutoCompleteStatesTest`.  The objective 
is to get all test methods in this class to pass.  The unit test is testing the method
`filterStates` in the class `AutoCompleteStates`.  

Imagine you are building autocomplete 
functionality for an input box where a user can start typing to select a state.
Given what the user types, this method returns a list of zero or more states that match
based on the rules below:
1) Match exact abbreviation (Should be case-insensitive)
2) Match start of state name
3) Match any part of state name

For the user to get the most relevant matches, it is important that the matches are
returned in the priority order listed above.  There should be no duplicate states 
returned by the method.

To run the tests, from the command line navigate to the directory where this file is located.  Execute the following command.

####Mac or Linux
`./gradlew test`

####Windows
`gradlew.bat test`
