#!/usr/bin/env bash

docker run -d --name s6r-zookeeper jplock/zookeeper:3.4.6
docker run -d --name s6r-kafka --link s6r-zookeeper:zookeeper ches/kafka

ZK_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' s6r-zookeeper)
KAFKA_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' s6r-kafka)

echo "Zookeeper IP: $ZK_IP"
echo "Kafka IP: $KAFKA_IP"

docker run --rm ches/kafka kafka-topics.sh --create --topic mails --replication-factor 1 --partitions 1 --zookeeper $ZK_IP:2181
