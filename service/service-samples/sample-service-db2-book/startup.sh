#!/bin/bash
# Creates and starts up a docker image for db2
docker run -itd --name db2 --restart unless-stopped -e DBNAME=testdb -v /Users/tisla4/Desktop/db2-book:/database -e DB2INST1_PASSWORD=password -e LICENSE=accept -p 50000:50000 --privileged=true ibmcom/db2

sleep 300

# Gets into the docker container
docker exec -it db2 bash -c "su - db2inst1"

# starts the db2 container
db2start

# connect to the db (named above in the first command)
db2 connect to testdb

# create the book table
db2 "create table BOOK (id int, title varchar(50), author varchar(50), description varchar(300))"