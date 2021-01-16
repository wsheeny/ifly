#!/bin/bash -e
ps -ef | grep aurora-0.0.1-SNAPSHOT.jar | grep -v grep | cut -c 9-15 | xargs kill
