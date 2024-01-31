#!/bin/bash

# Copyright 2020 American Express Travel Related Services Company, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
# in compliance with the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software distributed under the License
# is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
# or implied. See the License for the specific language governing permissions and limitations under
# the License.

# Creates and starts up a docker image for db2
docker run -itd --name db2 --restart unless-stopped -e DBNAME=testdb -v /insert/path/here:/database -e DB2INST1_PASSWORD=password -e LICENSE=accept -p 50000:50000 --privileged=true ibmcom/db2

sleep 300

# Gets into the docker container
docker exec -it db2 bash -c "su - db2inst1"

# starts the db2 container
db2start

# connect to the db (named above in the first command)
db2 connect to testdb

# create the book table
db2 "create table BOOK (id int, title varchar(50), author varchar(50), description varchar(300))"
