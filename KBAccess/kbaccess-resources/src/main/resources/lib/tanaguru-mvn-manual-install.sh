#!/bin/bash
mvn install:install-file -DgroupId=org.opens -DartifactId=commons-entity -Dversion=1.5.2 -Dpackaging=jar -Dfile=commons-entity-1.5.2.jar
mvn install:install-file -DgroupId=org.opens -DartifactId=commons-entity-jpa -Dversion=1.5.2 -Dpackaging=jar -Dfile=commons-entity-jpa-1.5.2.jar
mvn install:install-file -DgroupId=org.opens -DartifactId=tgol-api -Dversion=1.5.2 -Dpackaging=jar -Dfile=tgol-api-1.5.2.jar
mvn install:install-file -DgroupId=org.owasp -DartifactId=esapi -Dversion=2.0GA -Dpackaging=jar -Dfile=esapi.jar
