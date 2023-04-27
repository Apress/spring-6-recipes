#!/bin/bash

docker run --name s6r-redis -p 6379:6379 -d redis:7.0-alpine