#!/usr/bin/env bash

docker start cassio || docker run -d --name cassio cassandra:2.1.6
sleep 20s

docker exec cassio cqlsh -e "CREATE KEYSPACE spring WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };"
docker exec cassio cqlsh -e "CREATE TABLE spring.user (name varchar PRIMARY KEY, password varchar);"

./gradlew buildDocker
docker stop stat-run || true
docker rm stat-run || true

docker run -p 8080:8080 --name stat-run --link cassio:docker-cassandra statistics
