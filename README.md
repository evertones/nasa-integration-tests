# SDET-test

## Challenge
Create pilot Java test framework for testing NASA's open API >>>> Create pilot Java test framework for testing NASA’s open API

NASA has an open API: https://api.nasa.gov/index.html#getting-started. It grants access to different features e.g: Astronomy Picture of the Day, Mars Rover Photos, etc.

We would like to test different scenarios that the API offers:
1. Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
2. Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
3. Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
4. Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.
5. Write integration tests around the core functionality, not functional tests.

## Solution

### Build Tool

The solution was implemented using Gradle. 
Simple configuration was added in `gradle.properties`. In case they must change, they could be passed from a CI server by parameter, which prevents the need to recompile the code.
The Gradle Wrapper was used to ensure the version used to run the tests and also to facilitate the execution of the task since it does not require Gradle to be installed in your Operating System.

### Test Library for Integration Tests

The solution was written using RestAssured with JUnit.
The JSON response was mapped into Java classes similar to POJOs.
Jackson was used to deserialize the responses into Java objects.

A class to set up tests was created. It was claased `NasaOpenApiFixture`.
It stores default values that may be required by any Integration Tests in the given context.

### Results from NASA Open API 

The requests were made in their majority using the parameter to return only the `page=1`. It returns the first 25 items from a request.
The challenge requires to return the first 10 items, however I could not find any parameter to limit the number of photos returned, other than the parameter `page`. 

#### Tests from the Challenge

The tests required from items 1 to 4 are implemented in the class `NasaOpenApiTest`.

#### Tests from the Challenge (Around the core functionality)

The tests required from item 5 are implemented in the class `NasaOpenApiOtherTest`.

From my understanding, the functional tests for the API would verify if the "business logic" works as expected.
It seems it's what the challenge required from steps 1 to 4.

The "core functionality" of an API, could be considered the HTTP status that requests get from their responses, whether endpoints accept different HTTP methods, error handling, etc.
I implemented some cases around these concepts as part if item 5.

### Running the tests

To run the tests, please:
- open the terminal in your Operating System
- clone the repository to a specific folder
- cd into the folder
- run the following command (example with syntax for MacOS and Linux OS)
  - `./gradlew test --tests org.evertones.nasaOpenApi.v1.tests.NasaSuite`
- all tests must pass
