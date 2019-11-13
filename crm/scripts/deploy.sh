#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp target/crm-0.0.1-SNAPSHOT.jar \
    aleksandrgolubkin@188.120.249.92:/home/dru/

echo 'Restart server...'

ssh aleksandrgolubkin@188.120.249.92 << EOF
pgrep java | xargs kill -9
nohup java -jar crm-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo "Bye"