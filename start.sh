#!/bin/bash -e
java -jar aurora-community --spring.profiles.active=prod > log.txt 2>&1 &
