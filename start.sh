#!/bin/bash -e
java -jar rhapsody-backend.jar --spring.profiles.active=prod > log.txt 2>&1 &
