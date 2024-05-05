#!/bin/bash

set -x
set -e

INDEX=planning-features

if [ -z $1 ] ; then
    echo "es host required (eg http://127.0.0.1:9200)"
    exit 1
fi

./es-local.sh -s $1/_cat/indices | grep $INDEX
./es-local.sh -s -XDELETE $1/$INDEX
./es-local.sh -s -XPUT $1/$INDEX
./es-local.sh -s -XPUT $1/$INDEX/_mapping -d@mapping.json


