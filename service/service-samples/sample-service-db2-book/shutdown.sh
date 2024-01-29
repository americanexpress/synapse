#!/bin/bash

# Shuts down the db2 connectivity
db2 terminate
db2stop

# Optional - stop the docker container that spun up. These commands would have to be run yourself to avoid messing with any required docker containers
# list all docker containers
# docker ps
# stop the container
# docker stop <insert the db2 container id>
# remove the container
# docker rm <insert the db2 container id>



