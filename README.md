End-To-End Tests with Java and Selenium
=======================================

This repository is a simple example project to highlight the usual approach I
undertake when creating end-to-end tests. This repository uses:

- Cucumber/Gherkin feature files so that even non-technical colleagues can
  easily participate in the testing process
- Code organisation in page objects for reusability
- "Flexible testing": The tests are adjustable and can test the webpage both in
  mobile as well as in desktop viewport
    - Settings set via environment variables
    - Other settings I usually implement:
        - Headless vs. non-headless browser
        - A different regional version of the web app, i.e. US vs. France
        - Targetting a specific deployment URL
        - Testing against local development vs. production state
- "Smart abstractions": Elements which are prone to change, i.e. HTML selectors,
  are collected in their own sections so that the project can easily be picked
  up by other colleagues

# How to Run
- Run the tests with `mvn test`, which defaults to desktop viewport
- Set `BREAKPOINT=mobile` to run the tests in mobile viewport:
```powershell
mvn -DargLine="-DBREAKPOINT=mobile" test
```
