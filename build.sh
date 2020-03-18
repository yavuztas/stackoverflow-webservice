#!/bin/sh
mvn clean install
docker container rm stackoverflow-webservice
docker build -t boilerplates/stackoverflow-webservice-0.0.1 .
docker run -p 8080:8000 --name stackoverflow-webservice -e JAVA_OPTS=-Dserver.port=8000 boilerplates/stackoverflow-webservice-0.0.1
