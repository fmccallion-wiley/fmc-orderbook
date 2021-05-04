#!/bin/bash

# Set up application.properties
cat >/application.properties <<_END_
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://orderbookdb:3306/orderbook
spring.datasource.username=${DBUSER}
spring.datasource.password=${DBPASSWORD}
management.endpoint.health.show-details=always
management.endpoints.web.base-path=/
management.endpoints.web.path-mapping.health=status
_END_

# Wait for DB to start
counter=0

while ! java $JAVA_OPTS -jar /app.jar
do
  if (( counter > 10 ))
  then
    echo "Failed to connect with database" 1>&2
    exit 1
  fi
  ((counter=counter+1))
  sleep 15
done
