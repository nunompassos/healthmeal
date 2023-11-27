#!/bin/bash

mvn clean install --also-make --batch-mode --projects :orders &&
java -jar /usr/src/mymaven/services/orders/target/orders.jar
