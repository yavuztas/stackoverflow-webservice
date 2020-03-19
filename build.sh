#!/bin/sh
mvn clean install
docker container rm stackoverflow-webservice
docker build -t devyavuztas/stackoverflow-webservice .
docker run -p 8080:8080 --name stackoverflow-webservice devyavuztas/stackoverflow-webservice
