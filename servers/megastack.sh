#!/bin/bash -e
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <db_username> <db_password>"
    exit 1
fi

export MAVEN_OPTS="-Dfile.encoding=UTF8 -XX:MaxPermSize=512m -Xms1024m -Xmx1024m -Xss16M"
cd ../oid-service
mvn install -Dmaven.test.skip=true
cd ../servers
mvn install jetty:run -Ddbuser=$1 -Ddbpassword=$2

