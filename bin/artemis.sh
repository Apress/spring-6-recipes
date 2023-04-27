#!/bin/bash

docker run -e AMQ_USER=admin -e AMQ_PASSWORD=admin -e --relax-jolokia --name s6r-artemis -p 61616:61616 -p 8161:8161 quay.io/artemiscloud/activemq-artemis-broker:artemis.2.27.0
