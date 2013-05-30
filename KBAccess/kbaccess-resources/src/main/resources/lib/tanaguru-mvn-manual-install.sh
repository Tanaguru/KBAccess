#!/bin/bash

# Tanaguru libs
mvn install:install-file -DgroupId=org.opens -DartifactId=commons-entity -Dversion=1.5.2 -Dpackaging=jar -Dfile=commons-entity-1.5.2.jar
mvn install:install-file -DgroupId=org.opens -DartifactId=commons-entity-jpa -Dversion=1.5.2 -Dpackaging=jar -Dfile=commons-entity-jpa-1.5.2.jar
mvn install:install-file -DgroupId=org.opens -DartifactId=tgol-api -Dversion=1.5.2 -Dpackaging=jar -Dfile=tgol-api-1.5.2.jar
