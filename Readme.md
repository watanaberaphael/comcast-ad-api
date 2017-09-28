Ad API
==================================================================

 Exercise for Comcast

Index
------
 * [Requirements](#Requirements)
 * [Usage](#Usage)
 * [Installation](#Installation)
 * [Run Application](#Run Application)
 * [Integration Tests](#Integration Tests)
 
Requirements
-------------
 * JDK 8
 * Maven 3 
 
Usage
------
 * Language: **Java 8** 
 * Service: **REST**
 * **Maven Project**
 * Frameworks
   * **Sprint Boot**
 
Installation
-------------

    mvn clean install
   
Run Application
---------------- 
   
   This application is a Spring Boot Application. Then, to run it, follow these instructions:
   
   By IDE (IntelliJ or Eclipse):
   - Build the project with maven command: mvn clean install
   - Go to the class com.rnaka.comcast.ad.Application 
   - There is a static void main, run it
   
   By Command Line:
   - Go to the root project path
   - Run mvn clean install
   - mvn package && java -jar target/ad-api-1.0.0.jar
    
   The application runs on port 8080.
   
   
   To test the services, there are two ways:
   
   Automatic integration tests
   - Go to Integration Tests section
   
   Manual integration tests
   - use Postman with project file that is in the repository: 
   /resources/Comcast - Ad API.postman_collection
   
Integration Tests
------------------

    Package: com.rnaka.comcast.ad.test.integration

    Test Classes:
    CreateAdCampaignViaPOSTTest     ->      To create Ad
    FetchAdCampaignForPartnerTest   ->      To get Ad by Partner
    PlusListAllAdTest               ->      To list all Ads
    
Unit Tests
-----------

    Package: com.rnaka.comcast.ad.test.unit