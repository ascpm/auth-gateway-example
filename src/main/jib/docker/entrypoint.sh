#!/bin/sh

#echo -e "\nChecking java..."
java -version

#echo -e "\nCreating Application configuration directories..."
mkdir -p /etc/jwt/db
mkdir -p /var/jwt/log
mkdir -p /var/jwt/pid

echo -e "\nRunning Application..."
exec java -Xms512m -Xmx2048M -Dspring.profiles.active=production -jar docker/jwt/war/jwt.war
