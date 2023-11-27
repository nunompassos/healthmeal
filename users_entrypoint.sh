#!/bin/bash

mvn clean install --also-make --batch-mode --projects :users &&
java -jar /usr/src/mymaven/services/users/target/users.jar
