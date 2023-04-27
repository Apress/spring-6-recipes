#!/bin/bash

docker run --name s6r-neo4j -e NEO4J_AUTH=none -p 7474:7474 -p 7687:7687 -d neo4j:4.4-community
