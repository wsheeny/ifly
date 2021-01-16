#!/bin/bash -e
java -jar aurora-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > log.txt 2>&1 &
