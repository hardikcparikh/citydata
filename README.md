#citydata
Coding challange for city data.
Functional Requirement.
  This project is demo for spring restful webservice. It should expose endpoint “/connected”. This endpoint should accept origin city and destination city and it should give result as Yes or No based on connection between cities.

Configuration
  We need to prepare city.txt csv file in order to store list of connected cities and make sure this file is available in class path.

Technical Detail

Following files are included in this project.
1. DistanceApplication.java - This is main spring boot application file which will load spring boot application
2. DistanceController.java - This is spring boot controller which expose endpoint "/connected" and contains logic to load data from file into memory hashmap data structure and take decision if two cites are connected or not.
3. city.txt this is data file which contains list of connected cities in each single line separated with comma (,).

Unit Testing
Following files are included for unit testing purpose.
1. DistanceApplicationTest.java – This class has one test case to make sure that spring context able to load properly.
2. DistanceControllerTest.java – This class has two test cases which directly calls DistanceController method , one is to test all existing connection are returning result as “Yes” and second one is to make sure non existing connection are returning result as “No”.
3. HttpRequestTest.java – This class will send http request to server by calling localhost url, first test case is routeExist() to check for a valid combination of cities result is coming back as “Yes” second test is routeNotExist() to check for invalid combination of cities result is coming back as “No”.
Future Enhancement
  We can extend this project by including service and DAO layer to connect with database and retrieve cities connection data dynamically from DB.

Usage
  Once spring boot server is started we can open browser and can hit following urls.

1. http://localhost:8080/connected?origin=Boston&destination=Newark
      will return Yes
2. http://localhost:8080/connected?origin=Philadelphia&destination=Albany
will return No
