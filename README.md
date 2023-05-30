# Visibility algorithm

Spring Boot application that implements an algorithm to filter the products visible based on data provided in CSV files.

## Project Structure

The application is organized into the following packages:
- controller: Contains the controllers and exception handlers.
- service: Contains the service interfaces, implementations, and DTOs.
- swagger: Configuration for Swagger.

Inside the 'resources' directory:
- data/csv: CSV files to be processed.
- data/csv/test: CSV files for unit testing.

## Project Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven

## Installation

To install and set up the application, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project root directory.
3. Run the following command to build the application:

```
mvn clean install
```

## Prepare CSV files

Ensure that the required CSV files ('product.csv', 'size.csv', and 'stock.csv') are located in the path '/data/csv' inside '/resources'.

## Running the Application
To run the application, use the following command:

```
mvn spring-boot:run
```

- This command will start the Spring Boot application and make it accessible at http://localhost:8080.
- Send a GET request to http://localhost:8080/api/products to get the visible Products.

## Tests

The application includes unit tests for all services and the controller. Additionally, there is an integration test to validate the request.

- CsvReaderServiceImplTest: CsvReaderServiceImplTest: Validates the CSV reading in 'CsvReaderServiceImpl' with the test files in the '/test' folder.
- VisibilityServiceImplTest: Tests the methods in the 'VisibilityServiceImpl' service by mocking the CSV reading and verifying the functionality of the product filtering algorithm.
- VisibilityControllerIT: Tests the request in the 'VisibilityController' to ensure its correctness.

You can run the tests using the following command:
``` 
mvn test
``` 

## Docker

The application is containerized, and the 'Dockerfile' is located in the project's root directory. To deploy the application using Docker, execute the following commands in a terminal from the project's root directory: 
```    
docker build -t spring-boot-docker .
docker run -p 8080:8080 spring-boot-docker
```

## Swagger

The application provides API documentation using Swagger. Once the application is running, you can access the Swagger UI at http://localhost:8080/swagger-ui/#/.
The API documentation provides detailed information about the available endpoints, request/response formats, and example requests.

## Logger

The application utilizes logging to record important events and provide debugging information.
The default log level is DEBUG.

## Data Structures Used in the Algorithm

- `List` Used to store and iterate over products.
- `Map` Utilized for quick access and lookup of sizes associated with a product or the stock associated with sizes. 
Using a map allows efficient retrieval of desired elements without the need for iterating over the entire list.

## Algorithm Time Complexity

The algorithm has a time complexity of O(n*m), where 'n' represents the number of products and 'm' represents the number of sizes.
In the worst-case scenario, all sizes need to be iterated for each product. However, utilizing maps allows for more efficient filtering and retrieval, reducing the need for additional iterations.
