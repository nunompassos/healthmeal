#!/bin/bash

mvn clean install --also-make --batch-mode --projects :products &&
java -jar /usr/src/mymaven/services/products/target/products.jar
