#!/bin/bash -e
ps -ef | grep rhapsody-backend.jar | grep -v grep | cut -c 9-15 | xargs kill
