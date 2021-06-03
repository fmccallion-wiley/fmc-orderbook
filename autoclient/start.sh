#!/bin/bash

while :
do
  ((mins = 60 * DELAYMINS))
  mins=$(echo "" | awk -v seed="$RANDOM" -v mins="$mins" 'BEGIN{srand(seed)}{print int(rand()*mins)}')
  numconn=$(echo "" | awk -v seed="$RANDOM" -v mins="$mins" 'BEGIN{srand(seed)}{print int(rand()*1000)}')
  x=0
  curl $appname/order66 >/dev/null 2>&1
  while (( x < numconn ))
  do
    curl $appname/history >/dev/null 2>&1 &
    ((x=x+1))
  done
  /app/autoclient.py
  echo "Sleeping $mins"
  sleep $mins
done
