# Synapse API Rest Imperative

## Description


Status code reference https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html

## Request Datasource

- **Headers** 
- **Payload** 
- **Request Param** 
- **Query String** 

/api/v1/books/1?page=1&size=10

### Create

POST | /api/v1/books

#### Request

```json
{
  "title": "",
  "author": ""
}
```

#### Response

**201**  No Content

**400** Bad Request

```json
{
  "code": "GENERIC_4XX_ERROR",
  "message": "Invalid Request.",
  "moreInfo": null,
  "developerMessage": "Invalid identity element"
}
```

**500** Server Error

```json
{
  "code": "GENERIC_5XX_ERROR",
  "message": "Invalid Request.",
  "moreInfo": null,
  "developerMessage": "Invalid identity element"
}
```


### Read

**200** Success

```json
{
  "title": "",
  "author": ""
}
```

**202** - Request has been accepted for processing, but the processing has not been finished yet. The request may or may not be completed when the processing eventually takes place.

**206** Partial Content

**404** - Server either did not find a current representation for the requested resource or is trying to hide its existence from an unauthorized client.

### Update

**204** No Content

**404** - Server either did not find a current representation for the requested resource or is trying to hide its existence from an unauthorized client.

### Delete

**204** No Content

**404** - Server either did not find a current representation for the requested resource or is trying to hide its existence from an unauthorized client.