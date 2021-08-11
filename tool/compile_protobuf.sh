#!/bin/sh

DIR=`cd \`dirname ${BASH_SOURCE[0]}\`/.. && pwd`

protoc HomsDemo.proto --java_out=${DIR}/homs-demo-client/src/main/java --proto_path=${DIR}/homs-demo-client/src/main/java/com/coder4/homs/demo/
