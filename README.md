# Please set the following environment variables before running
 ```
 	export SAUCE_USER_NAME=your_username
 	export SAUCE_API_KEY=your_access_key
 ```

## Running the tests:

**From the project root:**
* to run Junit tests: ```./gradlew test```
* to run Cucumber scenarios ```./run_cucumber.sh```


**The following scenarios will be run:**

```
 Scenario: Check unchecked box
   Given page url is "https://saucelabs.com/test/guinea-pig"
   When unchecked checkbox is clicked
   Then unchecked checkbox selected state should be "true"

 Scenario: Uncheck checked box
   Given page url is "https://saucelabs.com/test/guinea-pig"
   When checked checkbox is clicked
   Then checked checkbox selected state should be "false"

 Scenario: Enter email
   Given page url is "https://saucelabs.com/test/guinea-pig"
   When email is entered as "foo@bar.com"
   Then email should show "foo@bar.com"

 Scenario: Enter and submit
   Given page url is "https://saucelabs.com/test/guinea-pig"
   And comments are entered as "Hello, world!"
   When form is submitted
   Then your comments section should end with "Hello, world!"

 Scenario: Uncheck checked box and submit
   Given page url is "https://saucelabs.com/test/guinea-pig"
   And checked checkbox is clicked
   When form is submitted
   Then checked checkbox selected state should be "true"

 Scenario: Check unchecked box and submit
   Given page url is "https://saucelabs.com/test/guinea-pig"
   And unchecked checkbox is clicked
   When form is submitted
   Then unchecked checkbox selected state should be "false"
```

**On the following OS/VERSION/BROWSER:**

```
["Windows 7",  "41", "chrome"]
["Windows XP", "8",  "internet explorer"]
["Windows 7",  "9",  "internet explorer"]
```



## Known Issue(s):

* Cucumber tests rely on bash to be parallel.
* Reporting is only available through Sauce Labs Dashboard due to concurrency management issues.

## Remarks:

* Given time developing a solution similar to the Sauce Labs junit extension would be best.
* Some portions of the code included are borrowed from sample projects provided bt Sauce Labs and/or their users.