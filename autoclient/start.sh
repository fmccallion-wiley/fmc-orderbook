#!/bin/bash

while :
do
  ((mins = 60 * DELAYMINS))
  mins=$(echo "" | awk -v seed="$RANDOM" -v mins="$mins" 'BEGIN{srand(seed)}{print int(rand()*mins)}')
  /app/autoclient.py
  echo "Sleeping $mins"
  sleep $mins
done
