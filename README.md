# Please set the following environment variables before running
 ```
 	export SAUCE_USER_NAME=your_username
 	export SAUCE_API_KEY=your_access_key
 ```
## Running the tests:

* to run Junit tests: ```./gradlew test```
* to run Cucumber scenarios ```./run_cucumber.sh```

From the project root

## Known Issue(s):

* Cucumber tests rely on bash to be parallel.
* Reporting is only available through Sauce Labs Dashboard due to concurrency management issues.

## Remarks:

* Given time developing a solution similar to the Sauce Labs junit extension would be best.
* Some portions of the code included are borrowed from sample projects provided bt Sauce Labs and/or their users.