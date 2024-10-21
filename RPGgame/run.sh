#!/bin/bash

mvn clean package

if [ -z "$1" ]; then
  echo "Usage: ./run_game.sh [console|gui]"
  exit 1
fi

java -jar target/RPGgame-1.0.jar $1