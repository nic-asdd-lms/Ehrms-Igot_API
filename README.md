# Ehrms-Igot_API
API to fetch analytics data of child organisations

## Prerequisites
1. Java version 17
2. Maven version 3.8.8

## Run
1. Set the value of `RESPONSE_FILE_PATH` in `util/Constants.java` to the value of `RESPONSE_PATH` in https://github.com/nic-asdd-lms/EhrmsDashboard/blob/main/src/main/java/igot/ehrms/util/Constants.java
2. `mvn clean install`
3. `java -jar target/ehrms-0.0.1-SNAPSHOT.jar`
2. Call the API `localhost:8000/apis/igot/analytics/<mapid>`
