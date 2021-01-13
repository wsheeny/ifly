#!/bin/bash -e
java -jar amor.jar --spring.profiles.active=prod > log.txt 2>&1 &
