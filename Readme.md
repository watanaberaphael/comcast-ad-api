Ad API
==================================================================

 Exercise for Comcast

Index
------
 * [Requirements](#Requirements)
 * [Usage](#Usage)
 * [Installation](#Installation)
 * [Run Application](#RunApplication)
 * [Integration Tests](#IntegrationTests)
 * [Unit Tests](#UnitTests)
 * [Bonus Exercises](#BonusExercises)
 
 
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
   - Run mvn package && java -jar target/ad-api-1.0.0.jar
    
    The application runs on port 8080.
   
Test Application
----------------    
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
    
    
Bonus Exercises
----------------

- Describe a fault tolerant deployment topology for your application, and the types of failures it would and would not 
be resilient to.

    A good solution for that application's deployment would be using Docker and something to manage it, such as 
    Openshift or a plain Kubernetes. In this way, adding health checks it's possible to automate and create scalability.
    In case it fails, the solution can continue to work without falls.
    
    
- Discuss the advantages and disadvantages of your persistence mechanism.

    As I'm using the memory mechanism which has as its biggest advantage the faster access of data. There are, however, 
    more disadvantages than advantages. Although the memory mechanism is faster, it consumes a lot of RAM memory and 
    there is a limited space of that. Further, the data is saved temporarily and it can only be reached by the current 
    instance. For all these reasons, the memory mechanism is not the best way for a production application.
     
    A solution for ad campaigns should be fast and scalable. Then, I would use persistence mechanism in a NoSql database
    which can provide a good environment to create a reliable and scalable application.
    
    
- Add a URL to return a list of all campaigns as JSON.

    Created a service GET /ad that lists all Ads
    
    Integration Tests:
        - com.rnaka.comcast.ad.test.PlusListAllAdTest
        
    Postman:
        - \resources\Comcast - Ad API.postman_collection
        - Request: GET  Ad - List  