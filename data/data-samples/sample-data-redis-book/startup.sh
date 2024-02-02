#!/bin/bash

# Download redis image and run container
docker run -d --name books-redis -p 6379:6379 redis

# Add data into database
docker exec -i books-redis redis-cli -h localhost -p 6379 < books.redis

