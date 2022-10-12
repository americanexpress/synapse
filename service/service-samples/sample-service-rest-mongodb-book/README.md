# sample-service-rest-mongodb-book

## Description

- This is a sample module demonstrating the end-to-end integration of synapse-service-rest and sample-data-mongodb-book.

### Dependencies

        <dependency>
            <groupId>io.americanexpress.synapse</groupId>
            <artifactId>synapse-service-rest</artifactId>
        </dependency>
        <dependency>
            <groupId>io.americanexpress.synapse</groupId>
            <artifactId>sample-data-mongodb-reactive</artifactId>
        </dependency>

## Usage

- To run the application, start the ```BookApplication``` 
- The following CRUD operations will be available:

### Create
```
POST localhost:8080/v1/books
```
Request:
```json
{
	"title": "Alice In Wonderland",
	"author": "Lewis Carroll"
}
```
Response:
```201 CREATED```

### READ
```
POST localhost:8080/v1/books/multiple_results
```
Request:
```json
{
	"title": "Alice In Wonderland"
}
```
Successful Response:
```json
[
    {
        "body": {
            "title": "Alice In Wonderland",
            "author": "Lewis Carroll"
        },
        "statusCodeValue": 200,
        "statusCode": "200 OK"
    }
]
```
### UPDATE
```
PUT localhost:8080/v1/books
```
Request:
```json
{
	"title": "Alice In Wonderland",
	"author": "Lewis Carroll",
	"numberOfCopies": 5
}
```
Successful Response:
```204 NO CONTENT```

### DELETE
```
DELETE localhost:8080/v1/books/Alice In Wonderland
```
Successful Response:
```204 NO CONTENT```
