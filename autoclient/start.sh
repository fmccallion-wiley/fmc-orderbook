#!/bin/bash

while :
do
  ((mins = 60 * DELAYMINS))
  mins=$(echo "" | awk -v seed="$RANDOM" -v mins="$mins" 'BEGIN{srand(seed)}{print int(rand()*mins)}')
  numconn=$(echo "" | awk -v mins="$mins" 'BEGIN{srand(1000)}{print int(rand()*10000)}')
  x=0
  ./main.py order66
  while (( x < numconn ))
  do
    ./main.py history & #>/dev/null 2>&1 &
    ((x=x+1))
  done
  /app/autoclient.py
  echo "Sleeping $mins"
  sleep $mins
done
