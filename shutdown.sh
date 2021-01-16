#!/bin/bash -e
ps -ef | grep aurora-community | grep -v grep | cut -c 9-15 | xargs kill
