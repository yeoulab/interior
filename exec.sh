#! /bin/bash

/opt/tomcat/bin/shutdown.sh
mvn clean
mvn package

cp /home/yeoreumzzing/interior/target/customer-0.0.1-SNAPSHOT.war /opt/tomcat/webapps

/opt/tomcat/bin/startup.sh
