# sample-service-rest-native-book

## Description

- This is a sample module demonstrating creating a GraalVM Native Application using Spring Native and Synapse Service Rest.
- Status: experimental

## Requirements 
Please refer to https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-image-system-requirements

## Usage

- In the terminal run: `mvn -Pnative -DskipTests package` which performs the AOT (ahead-of-time) transformations required and build the native image.
- If successful, the generated executable `sample-service-rest-native-book` will show up in the `target` directory.
- To run the executable, in the terminal run: `target/sample-service-rest-native-book`

### Sample Request
`POST localhost:8080/v1/books/inquiry_results`
```json
{
	"title": "Alice In Wonderland",
	"author": "Lewis Carroll"
}
```

## Resources
- https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-build-tools 