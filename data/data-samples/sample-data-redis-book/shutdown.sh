#!/bin/bash

# Shuts down the redis connectivity
redis-cli shutdown nosave

# Remove container
docker rm books-redis
