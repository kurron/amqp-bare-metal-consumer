#!/bin/bash

HOST=${1:-localhost}
VHOST=${2:-/}
USERNAME=${3:-guest}
PASSWORD=${4:-guest}
PREFETCH_COUNT=${5:-1}
CONCURRENCY=${6:-1}

CMD="docker run \
            --cpus 1 \
            --env consumer_modvalue=1000 \
            --env spring_rabbitmq_host=${HOST} \
            --env spring_rabbitmq_virtual-host=${VHOST} \
            --env spring.rabbitmq.username=${USERNAME} \
            --env spring.rabbitmq.password=${PASSWORD} \
            --env spring.rabbitmq.listener.prefetch=${PREFETCH_COUNT} \
            --env spring.rabbitmq.listener.concurrency=${CONCURRENCY} \
            --env spring.rabbitmq.listener.max-concurrency=${CONCURRENCY} \
            --interactive  \
            --name amqp-producer \
            --network host \
            --rm \
            --tty \
            kurron/amqp-bare-metal-consumer:latest"

echo ${CMD}
${CMD}