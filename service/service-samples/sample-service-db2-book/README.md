# sample-service-rest-db2-book

## Description

- This is a sample module demonstrating the end-to-end integration of synapse-service-rest and sample-data-db2.

### Dependencies

        <dependencies>
        <dependency>
            <groupId>io.americanexpress.synapse</groupId>
            <artifactId>sample-data-db2-book</artifactId>
        </dependency>
        <dependency>
            <groupId>io.americanexpress.synapse</groupId>
            <artifactId>synapse-service-rest</artifactId>
        </dependency>
    </dependencies>

## Usage

- Local instance of DB2 must be running
- Use the startup.sh script to start the DB2 instance that also creates the BOOK table
  - Replace the /insert/path/here with the path to a folder you'd like to create the database in
- Update the following properties:
  - ```spring.datasource.username=db2inst1```
  - ```spring.datasource.password=the one set from the docker command```
  - ```spring.datasource.url=jdbc:db2://localhost:50000/BOOKS```
    - If you used a different port then replace the `50000` with the port from the command in `startup.sh`.
- Use the shutdown.sh script to stop the DB2 instance
  - There are additional commands inside this script that are for shutting down and deleting the docker container which you would have to run yourself manually
- To run the application, start the ```BookApplication``` 
- Postman collection is available in the ```src/test/resources``` folder.
