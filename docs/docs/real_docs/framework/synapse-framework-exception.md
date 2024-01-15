# synapse-framework-exception

## Description

- This is the synapse module that provides the two Exception classes you will ever need in your application and also an
  elegant mechanism to handle them. It provides several out-of-the-box functionalities like:

    - An ApplicationServerException to wrap the caught checked exceptions thrown by the application.
    - An ApplicationClientException to return a user-friendly message to the users when a warning or message needs to be
      returned.
    - A generic open to extension ErrorCode enum with the most common error codes used by any application.
    - A generic open to extension error-messages.properties file that uses the error codes from the ErrorCode enum
      mentioned above.

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-framework-exception</artifactId>
            <version>0.3.30-SNAPSHOT</version>
        </dependency>
```