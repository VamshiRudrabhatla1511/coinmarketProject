First, make sure you are reading this page with support from a Mark Down editor/plug-in

#What I need to run this test?
1.please check if you local computer has Chrome or Firefox browser installed.
2. Change the value of browser value to either chrome or safari if it is a mac. If it is a windows PC
enter it as either chromeWindows or firefoxWindows.

#java JDK(version)
Set the language level to Java 8 or higher - Lambda expressions, Annotations etc.

#Execution
##### To run front end tests using Selenium Webdriver
    *   mvn test -Dcucumber.options="--tags @sanity"
    
##### To run back end tests for Rest API tests using Rest Assured
    *   mvn test -Dcucumber.options="--tags @backendsanity"

  

#Framework
This is a maven driven framework with Cucumber BDD. Selenium Webdriver to drive UI tests, RestAssured to perform REST API testing.

#Architecture

##### src/test/resources
    * contains the feature files can be seggregated by folders if necessary in case of more features 
    * can write any number of scenarios in a feature file
    * We enable tagging at feature and scenario level so that you can run them as smoke, regression suites or any individual test cases as well if needed...
    
##### src/test/java
    * contains Page Objects such as commonPageObjects where every page extends the commonPageObjects
    * contains all Pages for the required application
    * contains step definitions for all BDD steps defined in feature files
    * These files usually end with 'steps' (recommended)
    * We have utils such as commonMethods where we implement re-usable methods


#Common Issues and Answers
##### I keep getting the error, “Unimplemented substep definition” in IntelliJ with Cucumber??
###### Ans : Uninstall Substeps IntelliJ Plugin. Cucumber for Java and Gherkin should be enough.


