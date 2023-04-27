#!/bin/bash
docker run --name s6r-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=vehicle -p 5432:5432 -d postgres
