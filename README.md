# Please set the following environment variables before running
 ```
 	export SAUCE_USERNAME=your_username
 	export SAUCE_ACCESS_KEY=your_access_key
 ```
## Running the tests:

* to run Junit tests: ```./gradlew test```
* to run Cucumber scenarios ```./gradlew cucumber```

From the project root

## Known Issue(s):

* Cucumber tests are not truly parallel; can be made to run parallel by feature, but not by scenario. Maven builds could
be better in that sense.