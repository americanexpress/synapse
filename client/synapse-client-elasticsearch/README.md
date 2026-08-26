# synapse-client-elasticsearch

## Description
The `synapse-client-elasticsearch` module provides a client interface for interacting with Elasticsearch within the Synapse framework. It includes functionalities for indexing, searching, and managing Elasticsearch documents.

## Features
- Indexing documents into Elasticsearch
- Searching documents with various query types
- Managing Elasticsearch indices
- Handling Elasticsearch exceptions

## Installation
To include the `synapse-client-elasticsearch` module in your project, add the following dependency to your `pom.xml`:

## Usage

Add the configuration to your `application.properties` file:

```properties
elastic-client.url={elastic-url}
elastic-client.username={elastic-username}
elastic-client.password={elastic-password}
```

- To utilize this module, add the following dependency to the pom.xml file:
```xml
<dependency>
    <groupId>io.americanexpress.synapse</groupId>
    <artifactId>synapse-client-elasticsearch</artifactId>
    <version>4.0.0</version>
</dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-client-graphql:0.3.32-SNAPSHOT'
```
