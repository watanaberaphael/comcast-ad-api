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
   
   This application is a Spring Boot Application. Then, to run it, you need to run the main class:
    com.rnaka.comcast.ad.Application.java
    
   The application runs on port 8080.
   
   To test the services, use Postman with project file that is in the repository: 
    /resources/Comcast - Ad API.postman_collection
   
Integration Tests
------------------

    Package: com.rnaka.comcast.ad.test.integration

    Test Classes:
    CreateAdCampaignViaPOSTTest     ->      To create Ad
    FetchAdCampaignForPartnerTest   ->      To get Ad
    PlusListAllAdTest               ->      To list Ad 
    
Unit Tests
-----------

    Package: com.rnaka.comcast.ad.test.unit