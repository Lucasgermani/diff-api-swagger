# Lucasgermani diff-api-swagger
## About:
This simple api recieves base64 encoded string in json format and generate the diff between them.

## Compile and deploy:
### Requiriments:

 - JDK 1.8
- Maven 3.5
- Git

### Compiling

 1. Clone this repository:
 > git clone git@github.com:Lucasgermani/diff-api-swagger.git
 2. Build the maven project
 > mvn clean install
 3. Execute the api jar
 > java -jar target/diff-api-swagger-1.0.0.jar
 4. Acess swagger via URL:
> http://localhost:8080/swagger/
Service is up and running and ready for test


## Usage:
### Left and right endpoints:

You can consume the api using the following URL's:
>http://localhost:8080/rest/api/v1/diff/{ID}/left
or
> http://localhost:8080/rest/api/v1/diff/{ID}/right

* You should pass the ID as a pathparam and the base64 string value as json:
>{
  "value": "d2Flcw=="
}

### Result endpoints:
After inputting data in both left and right endpoints, you can retrieve the result comparison using the following result endpoint:
>http://localhost:8080/rest/api/v1/diff/{ID}
* You should pass the ID as a pathparam
#### The result could be:
* If the strings are equal:
>{ "id": 123, "result": "EQUAL" }
* If the string got different sizes:
>{ "id": 123, "result": "DIFFERENT_SIZE" }
* If they are of the same size but different:

> {
>  "id": 123,
> "result": "DIFFERENT",
> "diffList": [
>  { "offset": 1, "length": 2 },
>  { "offset": 5, "length": 2 },
>  { "offset": 9, "length": 2 }
>  ]
>  }


### Tests
To run the tests, at the project directory run:
> mvn clean install
* It'll compile and run unit and integration tests
